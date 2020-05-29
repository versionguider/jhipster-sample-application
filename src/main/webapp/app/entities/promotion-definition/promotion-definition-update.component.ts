import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPromotionDefinition, PromotionDefinition } from 'app/shared/model/promotion-definition.model';
import { PromotionDefinitionService } from './promotion-definition.service';

@Component({
  selector: 'jhi-promotion-definition-update',
  templateUrl: './promotion-definition-update.component.html',
})
export class PromotionDefinitionUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    promotionDefinitionId: [],
    promoCode: [],
  });

  constructor(
    protected promotionDefinitionService: PromotionDefinitionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ promotionDefinition }) => {
      this.updateForm(promotionDefinition);
    });
  }

  updateForm(promotionDefinition: IPromotionDefinition): void {
    this.editForm.patchValue({
      id: promotionDefinition.id,
      promotionDefinitionId: promotionDefinition.promotionDefinitionId,
      promoCode: promotionDefinition.promoCode,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const promotionDefinition = this.createFromForm();
    if (promotionDefinition.id !== undefined) {
      this.subscribeToSaveResponse(this.promotionDefinitionService.update(promotionDefinition));
    } else {
      this.subscribeToSaveResponse(this.promotionDefinitionService.create(promotionDefinition));
    }
  }

  private createFromForm(): IPromotionDefinition {
    return {
      ...new PromotionDefinition(),
      id: this.editForm.get(['id'])!.value,
      promotionDefinitionId: this.editForm.get(['promotionDefinitionId'])!.value,
      promoCode: this.editForm.get(['promoCode'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPromotionDefinition>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}

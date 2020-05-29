import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPromotionDistribution, PromotionDistribution } from 'app/shared/model/promotion-distribution.model';
import { PromotionDistributionService } from './promotion-distribution.service';
import { IBranch } from 'app/shared/model/branch.model';
import { BranchService } from 'app/entities/branch/branch.service';
import { IDistrict } from 'app/shared/model/district.model';
import { DistrictService } from 'app/entities/district/district.service';
import { IPromotionDefinition } from 'app/shared/model/promotion-definition.model';
import { PromotionDefinitionService } from 'app/entities/promotion-definition/promotion-definition.service';

type SelectableEntity = IBranch | IDistrict | IPromotionDefinition;

type SelectableManyToManyEntity = IBranch | IDistrict;

@Component({
  selector: 'jhi-promotion-distribution-update',
  templateUrl: './promotion-distribution-update.component.html',
})
export class PromotionDistributionUpdateComponent implements OnInit {
  isSaving = false;
  branches: IBranch[] = [];
  districts: IDistrict[] = [];
  promotiondefinitions: IPromotionDefinition[] = [];

  editForm = this.fb.group({
    id: [],
    promotionDistributionId: [],
    name: [],
    explanation: [],
    branches: [],
    districts: [],
    promotionDefinition: [],
  });

  constructor(
    protected promotionDistributionService: PromotionDistributionService,
    protected branchService: BranchService,
    protected districtService: DistrictService,
    protected promotionDefinitionService: PromotionDefinitionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ promotionDistribution }) => {
      this.updateForm(promotionDistribution);

      this.branchService.query().subscribe((res: HttpResponse<IBranch[]>) => (this.branches = res.body || []));

      this.districtService.query().subscribe((res: HttpResponse<IDistrict[]>) => (this.districts = res.body || []));

      this.promotionDefinitionService
        .query()
        .subscribe((res: HttpResponse<IPromotionDefinition[]>) => (this.promotiondefinitions = res.body || []));
    });
  }

  updateForm(promotionDistribution: IPromotionDistribution): void {
    this.editForm.patchValue({
      id: promotionDistribution.id,
      promotionDistributionId: promotionDistribution.promotionDistributionId,
      name: promotionDistribution.name,
      explanation: promotionDistribution.explanation,
      branches: promotionDistribution.branches,
      districts: promotionDistribution.districts,
      promotionDefinition: promotionDistribution.promotionDefinition,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const promotionDistribution = this.createFromForm();
    if (promotionDistribution.id !== undefined) {
      this.subscribeToSaveResponse(this.promotionDistributionService.update(promotionDistribution));
    } else {
      this.subscribeToSaveResponse(this.promotionDistributionService.create(promotionDistribution));
    }
  }

  private createFromForm(): IPromotionDistribution {
    return {
      ...new PromotionDistribution(),
      id: this.editForm.get(['id'])!.value,
      promotionDistributionId: this.editForm.get(['promotionDistributionId'])!.value,
      name: this.editForm.get(['name'])!.value,
      explanation: this.editForm.get(['explanation'])!.value,
      branches: this.editForm.get(['branches'])!.value,
      districts: this.editForm.get(['districts'])!.value,
      promotionDefinition: this.editForm.get(['promotionDefinition'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPromotionDistribution>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }

  getSelected(selectedVals: SelectableManyToManyEntity[], option: SelectableManyToManyEntity): SelectableManyToManyEntity {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}

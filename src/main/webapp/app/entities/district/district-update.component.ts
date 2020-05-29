import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDistrict, District } from 'app/shared/model/district.model';
import { DistrictService } from './district.service';

@Component({
  selector: 'jhi-district-update',
  templateUrl: './district-update.component.html',
})
export class DistrictUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    code: [],
    explanation: [],
  });

  constructor(protected districtService: DistrictService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ district }) => {
      this.updateForm(district);
    });
  }

  updateForm(district: IDistrict): void {
    this.editForm.patchValue({
      id: district.id,
      code: district.code,
      explanation: district.explanation,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const district = this.createFromForm();
    if (district.id !== undefined) {
      this.subscribeToSaveResponse(this.districtService.update(district));
    } else {
      this.subscribeToSaveResponse(this.districtService.create(district));
    }
  }

  private createFromForm(): IDistrict {
    return {
      ...new District(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      explanation: this.editForm.get(['explanation'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDistrict>>): void {
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

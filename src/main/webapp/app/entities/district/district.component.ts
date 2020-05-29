import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDistrict } from 'app/shared/model/district.model';
import { DistrictService } from './district.service';
import { DistrictDeleteDialogComponent } from './district-delete-dialog.component';

@Component({
  selector: 'jhi-district',
  templateUrl: './district.component.html',
})
export class DistrictComponent implements OnInit, OnDestroy {
  districts?: IDistrict[];
  eventSubscriber?: Subscription;

  constructor(protected districtService: DistrictService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.districtService.query().subscribe((res: HttpResponse<IDistrict[]>) => (this.districts = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInDistricts();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IDistrict): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInDistricts(): void {
    this.eventSubscriber = this.eventManager.subscribe('districtListModification', () => this.loadAll());
  }

  delete(district: IDistrict): void {
    const modalRef = this.modalService.open(DistrictDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.district = district;
  }
}

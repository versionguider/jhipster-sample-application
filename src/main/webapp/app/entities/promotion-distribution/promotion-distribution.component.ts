import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPromotionDistribution } from 'app/shared/model/promotion-distribution.model';
import { PromotionDistributionService } from './promotion-distribution.service';
import { PromotionDistributionDeleteDialogComponent } from './promotion-distribution-delete-dialog.component';

@Component({
  selector: 'jhi-promotion-distribution',
  templateUrl: './promotion-distribution.component.html',
})
export class PromotionDistributionComponent implements OnInit, OnDestroy {
  promotionDistributions?: IPromotionDistribution[];
  eventSubscriber?: Subscription;

  constructor(
    protected promotionDistributionService: PromotionDistributionService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.promotionDistributionService
      .query()
      .subscribe((res: HttpResponse<IPromotionDistribution[]>) => (this.promotionDistributions = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPromotionDistributions();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPromotionDistribution): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPromotionDistributions(): void {
    this.eventSubscriber = this.eventManager.subscribe('promotionDistributionListModification', () => this.loadAll());
  }

  delete(promotionDistribution: IPromotionDistribution): void {
    const modalRef = this.modalService.open(PromotionDistributionDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.promotionDistribution = promotionDistribution;
  }
}

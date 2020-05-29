import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPromotionDistribution } from 'app/shared/model/promotion-distribution.model';
import { PromotionDistributionService } from './promotion-distribution.service';

@Component({
  templateUrl: './promotion-distribution-delete-dialog.component.html',
})
export class PromotionDistributionDeleteDialogComponent {
  promotionDistribution?: IPromotionDistribution;

  constructor(
    protected promotionDistributionService: PromotionDistributionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.promotionDistributionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('promotionDistributionListModification');
      this.activeModal.close();
    });
  }
}

import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPromotionDefinition } from 'app/shared/model/promotion-definition.model';
import { PromotionDefinitionService } from './promotion-definition.service';

@Component({
  templateUrl: './promotion-definition-delete-dialog.component.html',
})
export class PromotionDefinitionDeleteDialogComponent {
  promotionDefinition?: IPromotionDefinition;

  constructor(
    protected promotionDefinitionService: PromotionDefinitionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.promotionDefinitionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('promotionDefinitionListModification');
      this.activeModal.close();
    });
  }
}

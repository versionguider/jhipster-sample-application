import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPromotionDefinition } from 'app/shared/model/promotion-definition.model';
import { PromotionDefinitionService } from './promotion-definition.service';
import { PromotionDefinitionDeleteDialogComponent } from './promotion-definition-delete-dialog.component';

@Component({
  selector: 'jhi-promotion-definition',
  templateUrl: './promotion-definition.component.html',
})
export class PromotionDefinitionComponent implements OnInit, OnDestroy {
  promotionDefinitions?: IPromotionDefinition[];
  eventSubscriber?: Subscription;

  constructor(
    protected promotionDefinitionService: PromotionDefinitionService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.promotionDefinitionService
      .query()
      .subscribe((res: HttpResponse<IPromotionDefinition[]>) => (this.promotionDefinitions = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPromotionDefinitions();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPromotionDefinition): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPromotionDefinitions(): void {
    this.eventSubscriber = this.eventManager.subscribe('promotionDefinitionListModification', () => this.loadAll());
  }

  delete(promotionDefinition: IPromotionDefinition): void {
    const modalRef = this.modalService.open(PromotionDefinitionDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.promotionDefinition = promotionDefinition;
  }
}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPromotionDefinition } from 'app/shared/model/promotion-definition.model';

@Component({
  selector: 'jhi-promotion-definition-detail',
  templateUrl: './promotion-definition-detail.component.html',
})
export class PromotionDefinitionDetailComponent implements OnInit {
  promotionDefinition: IPromotionDefinition | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ promotionDefinition }) => (this.promotionDefinition = promotionDefinition));
  }

  previousState(): void {
    window.history.back();
  }
}

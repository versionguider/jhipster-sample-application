import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPromotionDistribution } from 'app/shared/model/promotion-distribution.model';

@Component({
  selector: 'jhi-promotion-distribution-detail',
  templateUrl: './promotion-distribution-detail.component.html',
})
export class PromotionDistributionDetailComponent implements OnInit {
  promotionDistribution: IPromotionDistribution | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ promotionDistribution }) => (this.promotionDistribution = promotionDistribution));
  }

  previousState(): void {
    window.history.back();
  }
}

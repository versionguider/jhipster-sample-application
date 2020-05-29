import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPromotionDistribution, PromotionDistribution } from 'app/shared/model/promotion-distribution.model';
import { PromotionDistributionService } from './promotion-distribution.service';
import { PromotionDistributionComponent } from './promotion-distribution.component';
import { PromotionDistributionDetailComponent } from './promotion-distribution-detail.component';
import { PromotionDistributionUpdateComponent } from './promotion-distribution-update.component';

@Injectable({ providedIn: 'root' })
export class PromotionDistributionResolve implements Resolve<IPromotionDistribution> {
  constructor(private service: PromotionDistributionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPromotionDistribution> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((promotionDistribution: HttpResponse<PromotionDistribution>) => {
          if (promotionDistribution.body) {
            return of(promotionDistribution.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new PromotionDistribution());
  }
}

export const promotionDistributionRoute: Routes = [
  {
    path: '',
    component: PromotionDistributionComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.promotionDistribution.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PromotionDistributionDetailComponent,
    resolve: {
      promotionDistribution: PromotionDistributionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.promotionDistribution.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PromotionDistributionUpdateComponent,
    resolve: {
      promotionDistribution: PromotionDistributionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.promotionDistribution.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PromotionDistributionUpdateComponent,
    resolve: {
      promotionDistribution: PromotionDistributionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.promotionDistribution.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];

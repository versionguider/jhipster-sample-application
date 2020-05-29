import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPromotionDefinition, PromotionDefinition } from 'app/shared/model/promotion-definition.model';
import { PromotionDefinitionService } from './promotion-definition.service';
import { PromotionDefinitionComponent } from './promotion-definition.component';
import { PromotionDefinitionDetailComponent } from './promotion-definition-detail.component';
import { PromotionDefinitionUpdateComponent } from './promotion-definition-update.component';

@Injectable({ providedIn: 'root' })
export class PromotionDefinitionResolve implements Resolve<IPromotionDefinition> {
  constructor(private service: PromotionDefinitionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPromotionDefinition> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((promotionDefinition: HttpResponse<PromotionDefinition>) => {
          if (promotionDefinition.body) {
            return of(promotionDefinition.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new PromotionDefinition());
  }
}

export const promotionDefinitionRoute: Routes = [
  {
    path: '',
    component: PromotionDefinitionComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.promotionDefinition.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PromotionDefinitionDetailComponent,
    resolve: {
      promotionDefinition: PromotionDefinitionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.promotionDefinition.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PromotionDefinitionUpdateComponent,
    resolve: {
      promotionDefinition: PromotionDefinitionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.promotionDefinition.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PromotionDefinitionUpdateComponent,
    resolve: {
      promotionDefinition: PromotionDefinitionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.promotionDefinition.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];

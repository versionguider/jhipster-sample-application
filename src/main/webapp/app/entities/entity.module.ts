import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'branch',
        loadChildren: () => import('./branch/branch.module').then(m => m.JhipsterSampleApplicationBranchModule),
      },
      {
        path: 'district',
        loadChildren: () => import('./district/district.module').then(m => m.JhipsterSampleApplicationDistrictModule),
      },
      {
        path: 'promotion-definition',
        loadChildren: () =>
          import('./promotion-definition/promotion-definition.module').then(m => m.JhipsterSampleApplicationPromotionDefinitionModule),
      },
      {
        path: 'promotion-distribution',
        loadChildren: () =>
          import('./promotion-distribution/promotion-distribution.module').then(
            m => m.JhipsterSampleApplicationPromotionDistributionModule
          ),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class JhipsterSampleApplicationEntityModule {}

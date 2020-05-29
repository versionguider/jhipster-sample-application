import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { PromotionDistributionComponent } from './promotion-distribution.component';
import { PromotionDistributionDetailComponent } from './promotion-distribution-detail.component';
import { PromotionDistributionUpdateComponent } from './promotion-distribution-update.component';
import { PromotionDistributionDeleteDialogComponent } from './promotion-distribution-delete-dialog.component';
import { promotionDistributionRoute } from './promotion-distribution.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(promotionDistributionRoute)],
  declarations: [
    PromotionDistributionComponent,
    PromotionDistributionDetailComponent,
    PromotionDistributionUpdateComponent,
    PromotionDistributionDeleteDialogComponent,
  ],
  entryComponents: [PromotionDistributionDeleteDialogComponent],
})
export class JhipsterSampleApplicationPromotionDistributionModule {}

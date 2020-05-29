import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { PromotionDefinitionComponent } from './promotion-definition.component';
import { PromotionDefinitionDetailComponent } from './promotion-definition-detail.component';
import { PromotionDefinitionUpdateComponent } from './promotion-definition-update.component';
import { PromotionDefinitionDeleteDialogComponent } from './promotion-definition-delete-dialog.component';
import { promotionDefinitionRoute } from './promotion-definition.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(promotionDefinitionRoute)],
  declarations: [
    PromotionDefinitionComponent,
    PromotionDefinitionDetailComponent,
    PromotionDefinitionUpdateComponent,
    PromotionDefinitionDeleteDialogComponent,
  ],
  entryComponents: [PromotionDefinitionDeleteDialogComponent],
})
export class JhipsterSampleApplicationPromotionDefinitionModule {}

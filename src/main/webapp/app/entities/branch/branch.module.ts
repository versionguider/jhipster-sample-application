import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { BranchComponent } from './branch.component';
import { BranchDetailComponent } from './branch-detail.component';
import { BranchUpdateComponent } from './branch-update.component';
import { BranchDeleteDialogComponent } from './branch-delete-dialog.component';
import { branchRoute } from './branch.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(branchRoute)],
  declarations: [BranchComponent, BranchDetailComponent, BranchUpdateComponent, BranchDeleteDialogComponent],
  entryComponents: [BranchDeleteDialogComponent],
})
export class JhipsterSampleApplicationBranchModule {}

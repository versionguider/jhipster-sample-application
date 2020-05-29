import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { PromotionDistributionDetailComponent } from 'app/entities/promotion-distribution/promotion-distribution-detail.component';
import { PromotionDistribution } from 'app/shared/model/promotion-distribution.model';

describe('Component Tests', () => {
  describe('PromotionDistribution Management Detail Component', () => {
    let comp: PromotionDistributionDetailComponent;
    let fixture: ComponentFixture<PromotionDistributionDetailComponent>;
    const route = ({ data: of({ promotionDistribution: new PromotionDistribution(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [PromotionDistributionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PromotionDistributionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PromotionDistributionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load promotionDistribution on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.promotionDistribution).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

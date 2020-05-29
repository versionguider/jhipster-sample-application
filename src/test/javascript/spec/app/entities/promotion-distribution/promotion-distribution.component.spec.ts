import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { PromotionDistributionComponent } from 'app/entities/promotion-distribution/promotion-distribution.component';
import { PromotionDistributionService } from 'app/entities/promotion-distribution/promotion-distribution.service';
import { PromotionDistribution } from 'app/shared/model/promotion-distribution.model';

describe('Component Tests', () => {
  describe('PromotionDistribution Management Component', () => {
    let comp: PromotionDistributionComponent;
    let fixture: ComponentFixture<PromotionDistributionComponent>;
    let service: PromotionDistributionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [PromotionDistributionComponent],
      })
        .overrideTemplate(PromotionDistributionComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PromotionDistributionComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PromotionDistributionService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new PromotionDistribution(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.promotionDistributions && comp.promotionDistributions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

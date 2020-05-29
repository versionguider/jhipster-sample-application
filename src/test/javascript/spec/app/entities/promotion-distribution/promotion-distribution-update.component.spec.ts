import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { PromotionDistributionUpdateComponent } from 'app/entities/promotion-distribution/promotion-distribution-update.component';
import { PromotionDistributionService } from 'app/entities/promotion-distribution/promotion-distribution.service';
import { PromotionDistribution } from 'app/shared/model/promotion-distribution.model';

describe('Component Tests', () => {
  describe('PromotionDistribution Management Update Component', () => {
    let comp: PromotionDistributionUpdateComponent;
    let fixture: ComponentFixture<PromotionDistributionUpdateComponent>;
    let service: PromotionDistributionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [PromotionDistributionUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(PromotionDistributionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PromotionDistributionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PromotionDistributionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PromotionDistribution(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new PromotionDistribution();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});

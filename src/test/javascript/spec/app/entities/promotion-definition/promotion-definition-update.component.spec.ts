import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { PromotionDefinitionUpdateComponent } from 'app/entities/promotion-definition/promotion-definition-update.component';
import { PromotionDefinitionService } from 'app/entities/promotion-definition/promotion-definition.service';
import { PromotionDefinition } from 'app/shared/model/promotion-definition.model';

describe('Component Tests', () => {
  describe('PromotionDefinition Management Update Component', () => {
    let comp: PromotionDefinitionUpdateComponent;
    let fixture: ComponentFixture<PromotionDefinitionUpdateComponent>;
    let service: PromotionDefinitionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [PromotionDefinitionUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(PromotionDefinitionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PromotionDefinitionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PromotionDefinitionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PromotionDefinition(123);
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
        const entity = new PromotionDefinition();
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

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { PromotionDefinitionComponent } from 'app/entities/promotion-definition/promotion-definition.component';
import { PromotionDefinitionService } from 'app/entities/promotion-definition/promotion-definition.service';
import { PromotionDefinition } from 'app/shared/model/promotion-definition.model';

describe('Component Tests', () => {
  describe('PromotionDefinition Management Component', () => {
    let comp: PromotionDefinitionComponent;
    let fixture: ComponentFixture<PromotionDefinitionComponent>;
    let service: PromotionDefinitionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [PromotionDefinitionComponent],
      })
        .overrideTemplate(PromotionDefinitionComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PromotionDefinitionComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PromotionDefinitionService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new PromotionDefinition(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.promotionDefinitions && comp.promotionDefinitions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

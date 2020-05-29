import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { PromotionDefinitionDetailComponent } from 'app/entities/promotion-definition/promotion-definition-detail.component';
import { PromotionDefinition } from 'app/shared/model/promotion-definition.model';

describe('Component Tests', () => {
  describe('PromotionDefinition Management Detail Component', () => {
    let comp: PromotionDefinitionDetailComponent;
    let fixture: ComponentFixture<PromotionDefinitionDetailComponent>;
    const route = ({ data: of({ promotionDefinition: new PromotionDefinition(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [PromotionDefinitionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PromotionDefinitionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PromotionDefinitionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load promotionDefinition on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.promotionDefinition).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

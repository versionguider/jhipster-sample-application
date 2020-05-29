import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPromotionDefinition } from 'app/shared/model/promotion-definition.model';

type EntityResponseType = HttpResponse<IPromotionDefinition>;
type EntityArrayResponseType = HttpResponse<IPromotionDefinition[]>;

@Injectable({ providedIn: 'root' })
export class PromotionDefinitionService {
  public resourceUrl = SERVER_API_URL + 'api/promotion-definitions';

  constructor(protected http: HttpClient) {}

  create(promotionDefinition: IPromotionDefinition): Observable<EntityResponseType> {
    return this.http.post<IPromotionDefinition>(this.resourceUrl, promotionDefinition, { observe: 'response' });
  }

  update(promotionDefinition: IPromotionDefinition): Observable<EntityResponseType> {
    return this.http.put<IPromotionDefinition>(this.resourceUrl, promotionDefinition, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPromotionDefinition>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPromotionDefinition[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}

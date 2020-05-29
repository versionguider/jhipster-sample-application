import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPromotionDistribution } from 'app/shared/model/promotion-distribution.model';

type EntityResponseType = HttpResponse<IPromotionDistribution>;
type EntityArrayResponseType = HttpResponse<IPromotionDistribution[]>;

@Injectable({ providedIn: 'root' })
export class PromotionDistributionService {
  public resourceUrl = SERVER_API_URL + 'api/promotion-distributions';

  constructor(protected http: HttpClient) {}

  create(promotionDistribution: IPromotionDistribution): Observable<EntityResponseType> {
    return this.http.post<IPromotionDistribution>(this.resourceUrl, promotionDistribution, { observe: 'response' });
  }

  update(promotionDistribution: IPromotionDistribution): Observable<EntityResponseType> {
    return this.http.put<IPromotionDistribution>(this.resourceUrl, promotionDistribution, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPromotionDistribution>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPromotionDistribution[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}

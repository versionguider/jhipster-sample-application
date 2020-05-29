import { IPromotionDistribution } from 'app/shared/model/promotion-distribution.model';

export interface IDistrict {
  id?: number;
  code?: number;
  explanation?: string;
  promotionDistributions?: IPromotionDistribution[];
}

export class District implements IDistrict {
  constructor(
    public id?: number,
    public code?: number,
    public explanation?: string,
    public promotionDistributions?: IPromotionDistribution[]
  ) {}
}

import { IPromotionDistribution } from 'app/shared/model/promotion-distribution.model';

export interface IBranch {
  id?: number;
  code?: number;
  explanation?: string;
  promotionDistributions?: IPromotionDistribution[];
}

export class Branch implements IBranch {
  constructor(
    public id?: number,
    public code?: number,
    public explanation?: string,
    public promotionDistributions?: IPromotionDistribution[]
  ) {}
}

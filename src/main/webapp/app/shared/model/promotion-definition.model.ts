import { IPromotionDistribution } from 'app/shared/model/promotion-distribution.model';

export interface IPromotionDefinition {
  id?: number;
  promotionDefinitionId?: number;
  promoCode?: string;
  branches?: IPromotionDistribution[];
}

export class PromotionDefinition implements IPromotionDefinition {
  constructor(
    public id?: number,
    public promotionDefinitionId?: number,
    public promoCode?: string,
    public branches?: IPromotionDistribution[]
  ) {}
}

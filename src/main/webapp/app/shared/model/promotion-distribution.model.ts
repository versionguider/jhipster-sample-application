import { IBranch } from 'app/shared/model/branch.model';
import { IDistrict } from 'app/shared/model/district.model';
import { IPromotionDefinition } from 'app/shared/model/promotion-definition.model';

export interface IPromotionDistribution {
  id?: number;
  promotionDistributionId?: number;
  name?: string;
  explanation?: string;
  branches?: IBranch[];
  districts?: IDistrict[];
  promotionDefinition?: IPromotionDefinition;
}

export class PromotionDistribution implements IPromotionDistribution {
  constructor(
    public id?: number,
    public promotionDistributionId?: number,
    public name?: string,
    public explanation?: string,
    public branches?: IBranch[],
    public districts?: IDistrict[],
    public promotionDefinition?: IPromotionDefinition
  ) {}
}

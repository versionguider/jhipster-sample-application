package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A PromotionDefinition.
 */
@Entity
@Table(name = "promotion_definition")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PromotionDefinition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "promotion_definition_id")
    private Integer promotionDefinitionId;

    @Column(name = "promo_code")
    private String promoCode;

    @OneToMany(mappedBy = "promotionDefinition")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<PromotionDistribution> branches = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPromotionDefinitionId() {
        return promotionDefinitionId;
    }

    public PromotionDefinition promotionDefinitionId(Integer promotionDefinitionId) {
        this.promotionDefinitionId = promotionDefinitionId;
        return this;
    }

    public void setPromotionDefinitionId(Integer promotionDefinitionId) {
        this.promotionDefinitionId = promotionDefinitionId;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public PromotionDefinition promoCode(String promoCode) {
        this.promoCode = promoCode;
        return this;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public Set<PromotionDistribution> getBranches() {
        return branches;
    }

    public PromotionDefinition branches(Set<PromotionDistribution> promotionDistributions) {
        this.branches = promotionDistributions;
        return this;
    }

    public PromotionDefinition addBranch(PromotionDistribution promotionDistribution) {
        this.branches.add(promotionDistribution);
        promotionDistribution.setPromotionDefinition(this);
        return this;
    }

    public PromotionDefinition removeBranch(PromotionDistribution promotionDistribution) {
        this.branches.remove(promotionDistribution);
        promotionDistribution.setPromotionDefinition(null);
        return this;
    }

    public void setBranches(Set<PromotionDistribution> promotionDistributions) {
        this.branches = promotionDistributions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PromotionDefinition)) {
            return false;
        }
        return id != null && id.equals(((PromotionDefinition) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PromotionDefinition{" +
            "id=" + getId() +
            ", promotionDefinitionId=" + getPromotionDefinitionId() +
            ", promoCode='" + getPromoCode() + "'" +
            "}";
    }
}

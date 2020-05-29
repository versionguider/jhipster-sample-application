package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A District.
 */
@Entity
@Table(name = "district")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class District implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private Integer code;

    @Column(name = "explanation")
    private String explanation;

    @ManyToMany(mappedBy = "districts")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<PromotionDistribution> promotionDistributions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public District code(Integer code) {
        this.code = code;
        return this;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getExplanation() {
        return explanation;
    }

    public District explanation(String explanation) {
        this.explanation = explanation;
        return this;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public Set<PromotionDistribution> getPromotionDistributions() {
        return promotionDistributions;
    }

    public District promotionDistributions(Set<PromotionDistribution> promotionDistributions) {
        this.promotionDistributions = promotionDistributions;
        return this;
    }

    public District addPromotionDistribution(PromotionDistribution promotionDistribution) {
        this.promotionDistributions.add(promotionDistribution);
        promotionDistribution.getDistricts().add(this);
        return this;
    }

    public District removePromotionDistribution(PromotionDistribution promotionDistribution) {
        this.promotionDistributions.remove(promotionDistribution);
        promotionDistribution.getDistricts().remove(this);
        return this;
    }

    public void setPromotionDistributions(Set<PromotionDistribution> promotionDistributions) {
        this.promotionDistributions = promotionDistributions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof District)) {
            return false;
        }
        return id != null && id.equals(((District) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "District{" +
            "id=" + getId() +
            ", code=" + getCode() +
            ", explanation='" + getExplanation() + "'" +
            "}";
    }
}

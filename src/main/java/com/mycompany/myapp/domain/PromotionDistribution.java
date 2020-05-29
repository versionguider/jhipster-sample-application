package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A PromotionDistribution.
 */
@Entity
@Table(name = "promotion_distribution")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PromotionDistribution implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "promotion_distribution_id")
    private Integer promotionDistributionId;

    @Column(name = "name")
    private String name;

    @Column(name = "explanation")
    private String explanation;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "promotion_distribution_branch",
               joinColumns = @JoinColumn(name = "promotion_distribution_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "branch_id", referencedColumnName = "id"))
    private Set<Branch> branches = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "promotion_distribution_district",
               joinColumns = @JoinColumn(name = "promotion_distribution_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "district_id", referencedColumnName = "id"))
    private Set<District> districts = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "branches", allowSetters = true)
    private PromotionDefinition promotionDefinition;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPromotionDistributionId() {
        return promotionDistributionId;
    }

    public PromotionDistribution promotionDistributionId(Integer promotionDistributionId) {
        this.promotionDistributionId = promotionDistributionId;
        return this;
    }

    public void setPromotionDistributionId(Integer promotionDistributionId) {
        this.promotionDistributionId = promotionDistributionId;
    }

    public String getName() {
        return name;
    }

    public PromotionDistribution name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExplanation() {
        return explanation;
    }

    public PromotionDistribution explanation(String explanation) {
        this.explanation = explanation;
        return this;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public Set<Branch> getBranches() {
        return branches;
    }

    public PromotionDistribution branches(Set<Branch> branches) {
        this.branches = branches;
        return this;
    }

    public PromotionDistribution addBranch(Branch branch) {
        this.branches.add(branch);
        branch.getPromotionDistributions().add(this);
        return this;
    }

    public PromotionDistribution removeBranch(Branch branch) {
        this.branches.remove(branch);
        branch.getPromotionDistributions().remove(this);
        return this;
    }

    public void setBranches(Set<Branch> branches) {
        this.branches = branches;
    }

    public Set<District> getDistricts() {
        return districts;
    }

    public PromotionDistribution districts(Set<District> districts) {
        this.districts = districts;
        return this;
    }

    public PromotionDistribution addDistrict(District district) {
        this.districts.add(district);
        district.getPromotionDistributions().add(this);
        return this;
    }

    public PromotionDistribution removeDistrict(District district) {
        this.districts.remove(district);
        district.getPromotionDistributions().remove(this);
        return this;
    }

    public void setDistricts(Set<District> districts) {
        this.districts = districts;
    }

    public PromotionDefinition getPromotionDefinition() {
        return promotionDefinition;
    }

    public PromotionDistribution promotionDefinition(PromotionDefinition promotionDefinition) {
        this.promotionDefinition = promotionDefinition;
        return this;
    }

    public void setPromotionDefinition(PromotionDefinition promotionDefinition) {
        this.promotionDefinition = promotionDefinition;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PromotionDistribution)) {
            return false;
        }
        return id != null && id.equals(((PromotionDistribution) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PromotionDistribution{" +
            "id=" + getId() +
            ", promotionDistributionId=" + getPromotionDistributionId() +
            ", name='" + getName() + "'" +
            ", explanation='" + getExplanation() + "'" +
            "}";
    }
}

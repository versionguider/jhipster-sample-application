package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.PromotionDistribution;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the PromotionDistribution entity.
 */
@Repository
public interface PromotionDistributionRepository extends JpaRepository<PromotionDistribution, Long> {

    @Query(value = "select distinct promotionDistribution from PromotionDistribution promotionDistribution left join fetch promotionDistribution.branches left join fetch promotionDistribution.districts",
        countQuery = "select count(distinct promotionDistribution) from PromotionDistribution promotionDistribution")
    Page<PromotionDistribution> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct promotionDistribution from PromotionDistribution promotionDistribution left join fetch promotionDistribution.branches left join fetch promotionDistribution.districts")
    List<PromotionDistribution> findAllWithEagerRelationships();

    @Query("select promotionDistribution from PromotionDistribution promotionDistribution left join fetch promotionDistribution.branches left join fetch promotionDistribution.districts where promotionDistribution.id =:id")
    Optional<PromotionDistribution> findOneWithEagerRelationships(@Param("id") Long id);
}

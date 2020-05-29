package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.PromotionDefinition;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PromotionDefinition entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PromotionDefinitionRepository extends JpaRepository<PromotionDefinition, Long> {
}

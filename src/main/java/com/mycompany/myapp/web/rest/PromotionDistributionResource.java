package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.PromotionDistribution;
import com.mycompany.myapp.repository.PromotionDistributionRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.PromotionDistribution}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PromotionDistributionResource {

    private final Logger log = LoggerFactory.getLogger(PromotionDistributionResource.class);

    private static final String ENTITY_NAME = "promotionDistribution";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PromotionDistributionRepository promotionDistributionRepository;

    public PromotionDistributionResource(PromotionDistributionRepository promotionDistributionRepository) {
        this.promotionDistributionRepository = promotionDistributionRepository;
    }

    /**
     * {@code POST  /promotion-distributions} : Create a new promotionDistribution.
     *
     * @param promotionDistribution the promotionDistribution to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new promotionDistribution, or with status {@code 400 (Bad Request)} if the promotionDistribution has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/promotion-distributions")
    public ResponseEntity<PromotionDistribution> createPromotionDistribution(@RequestBody PromotionDistribution promotionDistribution) throws URISyntaxException {
        log.debug("REST request to save PromotionDistribution : {}", promotionDistribution);
        if (promotionDistribution.getId() != null) {
            throw new BadRequestAlertException("A new promotionDistribution cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PromotionDistribution result = promotionDistributionRepository.save(promotionDistribution);
        return ResponseEntity.created(new URI("/api/promotion-distributions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /promotion-distributions} : Updates an existing promotionDistribution.
     *
     * @param promotionDistribution the promotionDistribution to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated promotionDistribution,
     * or with status {@code 400 (Bad Request)} if the promotionDistribution is not valid,
     * or with status {@code 500 (Internal Server Error)} if the promotionDistribution couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/promotion-distributions")
    public ResponseEntity<PromotionDistribution> updatePromotionDistribution(@RequestBody PromotionDistribution promotionDistribution) throws URISyntaxException {
        log.debug("REST request to update PromotionDistribution : {}", promotionDistribution);
        if (promotionDistribution.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PromotionDistribution result = promotionDistributionRepository.save(promotionDistribution);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, promotionDistribution.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /promotion-distributions} : get all the promotionDistributions.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of promotionDistributions in body.
     */
    @GetMapping("/promotion-distributions")
    public List<PromotionDistribution> getAllPromotionDistributions(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all PromotionDistributions");
        return promotionDistributionRepository.findAllWithEagerRelationships();
    }

    /**
     * {@code GET  /promotion-distributions/:id} : get the "id" promotionDistribution.
     *
     * @param id the id of the promotionDistribution to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the promotionDistribution, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/promotion-distributions/{id}")
    public ResponseEntity<PromotionDistribution> getPromotionDistribution(@PathVariable Long id) {
        log.debug("REST request to get PromotionDistribution : {}", id);
        Optional<PromotionDistribution> promotionDistribution = promotionDistributionRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(promotionDistribution);
    }

    /**
     * {@code DELETE  /promotion-distributions/:id} : delete the "id" promotionDistribution.
     *
     * @param id the id of the promotionDistribution to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/promotion-distributions/{id}")
    public ResponseEntity<Void> deletePromotionDistribution(@PathVariable Long id) {
        log.debug("REST request to delete PromotionDistribution : {}", id);

        promotionDistributionRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

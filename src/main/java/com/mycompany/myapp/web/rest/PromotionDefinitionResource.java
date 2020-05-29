package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.PromotionDefinition;
import com.mycompany.myapp.repository.PromotionDefinitionRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.PromotionDefinition}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PromotionDefinitionResource {

    private final Logger log = LoggerFactory.getLogger(PromotionDefinitionResource.class);

    private static final String ENTITY_NAME = "promotionDefinition";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PromotionDefinitionRepository promotionDefinitionRepository;

    public PromotionDefinitionResource(PromotionDefinitionRepository promotionDefinitionRepository) {
        this.promotionDefinitionRepository = promotionDefinitionRepository;
    }

    /**
     * {@code POST  /promotion-definitions} : Create a new promotionDefinition.
     *
     * @param promotionDefinition the promotionDefinition to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new promotionDefinition, or with status {@code 400 (Bad Request)} if the promotionDefinition has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/promotion-definitions")
    public ResponseEntity<PromotionDefinition> createPromotionDefinition(@RequestBody PromotionDefinition promotionDefinition) throws URISyntaxException {
        log.debug("REST request to save PromotionDefinition : {}", promotionDefinition);
        if (promotionDefinition.getId() != null) {
            throw new BadRequestAlertException("A new promotionDefinition cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PromotionDefinition result = promotionDefinitionRepository.save(promotionDefinition);
        return ResponseEntity.created(new URI("/api/promotion-definitions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /promotion-definitions} : Updates an existing promotionDefinition.
     *
     * @param promotionDefinition the promotionDefinition to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated promotionDefinition,
     * or with status {@code 400 (Bad Request)} if the promotionDefinition is not valid,
     * or with status {@code 500 (Internal Server Error)} if the promotionDefinition couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/promotion-definitions")
    public ResponseEntity<PromotionDefinition> updatePromotionDefinition(@RequestBody PromotionDefinition promotionDefinition) throws URISyntaxException {
        log.debug("REST request to update PromotionDefinition : {}", promotionDefinition);
        if (promotionDefinition.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PromotionDefinition result = promotionDefinitionRepository.save(promotionDefinition);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, promotionDefinition.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /promotion-definitions} : get all the promotionDefinitions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of promotionDefinitions in body.
     */
    @GetMapping("/promotion-definitions")
    public List<PromotionDefinition> getAllPromotionDefinitions() {
        log.debug("REST request to get all PromotionDefinitions");
        return promotionDefinitionRepository.findAll();
    }

    /**
     * {@code GET  /promotion-definitions/:id} : get the "id" promotionDefinition.
     *
     * @param id the id of the promotionDefinition to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the promotionDefinition, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/promotion-definitions/{id}")
    public ResponseEntity<PromotionDefinition> getPromotionDefinition(@PathVariable Long id) {
        log.debug("REST request to get PromotionDefinition : {}", id);
        Optional<PromotionDefinition> promotionDefinition = promotionDefinitionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(promotionDefinition);
    }

    /**
     * {@code DELETE  /promotion-definitions/:id} : delete the "id" promotionDefinition.
     *
     * @param id the id of the promotionDefinition to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/promotion-definitions/{id}")
    public ResponseEntity<Void> deletePromotionDefinition(@PathVariable Long id) {
        log.debug("REST request to delete PromotionDefinition : {}", id);

        promotionDefinitionRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

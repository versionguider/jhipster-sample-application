package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.PromotionDefinition;
import com.mycompany.myapp.repository.PromotionDefinitionRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PromotionDefinitionResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PromotionDefinitionResourceIT {

    private static final Integer DEFAULT_PROMOTION_DEFINITION_ID = 1;
    private static final Integer UPDATED_PROMOTION_DEFINITION_ID = 2;

    private static final String DEFAULT_PROMO_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PROMO_CODE = "BBBBBBBBBB";

    @Autowired
    private PromotionDefinitionRepository promotionDefinitionRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPromotionDefinitionMockMvc;

    private PromotionDefinition promotionDefinition;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PromotionDefinition createEntity(EntityManager em) {
        PromotionDefinition promotionDefinition = new PromotionDefinition()
            .promotionDefinitionId(DEFAULT_PROMOTION_DEFINITION_ID)
            .promoCode(DEFAULT_PROMO_CODE);
        return promotionDefinition;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PromotionDefinition createUpdatedEntity(EntityManager em) {
        PromotionDefinition promotionDefinition = new PromotionDefinition()
            .promotionDefinitionId(UPDATED_PROMOTION_DEFINITION_ID)
            .promoCode(UPDATED_PROMO_CODE);
        return promotionDefinition;
    }

    @BeforeEach
    public void initTest() {
        promotionDefinition = createEntity(em);
    }

    @Test
    @Transactional
    public void createPromotionDefinition() throws Exception {
        int databaseSizeBeforeCreate = promotionDefinitionRepository.findAll().size();
        // Create the PromotionDefinition
        restPromotionDefinitionMockMvc.perform(post("/api/promotion-definitions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(promotionDefinition)))
            .andExpect(status().isCreated());

        // Validate the PromotionDefinition in the database
        List<PromotionDefinition> promotionDefinitionList = promotionDefinitionRepository.findAll();
        assertThat(promotionDefinitionList).hasSize(databaseSizeBeforeCreate + 1);
        PromotionDefinition testPromotionDefinition = promotionDefinitionList.get(promotionDefinitionList.size() - 1);
        assertThat(testPromotionDefinition.getPromotionDefinitionId()).isEqualTo(DEFAULT_PROMOTION_DEFINITION_ID);
        assertThat(testPromotionDefinition.getPromoCode()).isEqualTo(DEFAULT_PROMO_CODE);
    }

    @Test
    @Transactional
    public void createPromotionDefinitionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = promotionDefinitionRepository.findAll().size();

        // Create the PromotionDefinition with an existing ID
        promotionDefinition.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPromotionDefinitionMockMvc.perform(post("/api/promotion-definitions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(promotionDefinition)))
            .andExpect(status().isBadRequest());

        // Validate the PromotionDefinition in the database
        List<PromotionDefinition> promotionDefinitionList = promotionDefinitionRepository.findAll();
        assertThat(promotionDefinitionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPromotionDefinitions() throws Exception {
        // Initialize the database
        promotionDefinitionRepository.saveAndFlush(promotionDefinition);

        // Get all the promotionDefinitionList
        restPromotionDefinitionMockMvc.perform(get("/api/promotion-definitions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(promotionDefinition.getId().intValue())))
            .andExpect(jsonPath("$.[*].promotionDefinitionId").value(hasItem(DEFAULT_PROMOTION_DEFINITION_ID)))
            .andExpect(jsonPath("$.[*].promoCode").value(hasItem(DEFAULT_PROMO_CODE)));
    }
    
    @Test
    @Transactional
    public void getPromotionDefinition() throws Exception {
        // Initialize the database
        promotionDefinitionRepository.saveAndFlush(promotionDefinition);

        // Get the promotionDefinition
        restPromotionDefinitionMockMvc.perform(get("/api/promotion-definitions/{id}", promotionDefinition.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(promotionDefinition.getId().intValue()))
            .andExpect(jsonPath("$.promotionDefinitionId").value(DEFAULT_PROMOTION_DEFINITION_ID))
            .andExpect(jsonPath("$.promoCode").value(DEFAULT_PROMO_CODE));
    }
    @Test
    @Transactional
    public void getNonExistingPromotionDefinition() throws Exception {
        // Get the promotionDefinition
        restPromotionDefinitionMockMvc.perform(get("/api/promotion-definitions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePromotionDefinition() throws Exception {
        // Initialize the database
        promotionDefinitionRepository.saveAndFlush(promotionDefinition);

        int databaseSizeBeforeUpdate = promotionDefinitionRepository.findAll().size();

        // Update the promotionDefinition
        PromotionDefinition updatedPromotionDefinition = promotionDefinitionRepository.findById(promotionDefinition.getId()).get();
        // Disconnect from session so that the updates on updatedPromotionDefinition are not directly saved in db
        em.detach(updatedPromotionDefinition);
        updatedPromotionDefinition
            .promotionDefinitionId(UPDATED_PROMOTION_DEFINITION_ID)
            .promoCode(UPDATED_PROMO_CODE);

        restPromotionDefinitionMockMvc.perform(put("/api/promotion-definitions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPromotionDefinition)))
            .andExpect(status().isOk());

        // Validate the PromotionDefinition in the database
        List<PromotionDefinition> promotionDefinitionList = promotionDefinitionRepository.findAll();
        assertThat(promotionDefinitionList).hasSize(databaseSizeBeforeUpdate);
        PromotionDefinition testPromotionDefinition = promotionDefinitionList.get(promotionDefinitionList.size() - 1);
        assertThat(testPromotionDefinition.getPromotionDefinitionId()).isEqualTo(UPDATED_PROMOTION_DEFINITION_ID);
        assertThat(testPromotionDefinition.getPromoCode()).isEqualTo(UPDATED_PROMO_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingPromotionDefinition() throws Exception {
        int databaseSizeBeforeUpdate = promotionDefinitionRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPromotionDefinitionMockMvc.perform(put("/api/promotion-definitions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(promotionDefinition)))
            .andExpect(status().isBadRequest());

        // Validate the PromotionDefinition in the database
        List<PromotionDefinition> promotionDefinitionList = promotionDefinitionRepository.findAll();
        assertThat(promotionDefinitionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePromotionDefinition() throws Exception {
        // Initialize the database
        promotionDefinitionRepository.saveAndFlush(promotionDefinition);

        int databaseSizeBeforeDelete = promotionDefinitionRepository.findAll().size();

        // Delete the promotionDefinition
        restPromotionDefinitionMockMvc.perform(delete("/api/promotion-definitions/{id}", promotionDefinition.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PromotionDefinition> promotionDefinitionList = promotionDefinitionRepository.findAll();
        assertThat(promotionDefinitionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.PromotionDistribution;
import com.mycompany.myapp.repository.PromotionDistributionRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PromotionDistributionResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class PromotionDistributionResourceIT {

    private static final Integer DEFAULT_PROMOTION_DISTRIBUTION_ID = 1;
    private static final Integer UPDATED_PROMOTION_DISTRIBUTION_ID = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EXPLANATION = "AAAAAAAAAA";
    private static final String UPDATED_EXPLANATION = "BBBBBBBBBB";

    @Autowired
    private PromotionDistributionRepository promotionDistributionRepository;

    @Mock
    private PromotionDistributionRepository promotionDistributionRepositoryMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPromotionDistributionMockMvc;

    private PromotionDistribution promotionDistribution;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PromotionDistribution createEntity(EntityManager em) {
        PromotionDistribution promotionDistribution = new PromotionDistribution()
            .promotionDistributionId(DEFAULT_PROMOTION_DISTRIBUTION_ID)
            .name(DEFAULT_NAME)
            .explanation(DEFAULT_EXPLANATION);
        return promotionDistribution;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PromotionDistribution createUpdatedEntity(EntityManager em) {
        PromotionDistribution promotionDistribution = new PromotionDistribution()
            .promotionDistributionId(UPDATED_PROMOTION_DISTRIBUTION_ID)
            .name(UPDATED_NAME)
            .explanation(UPDATED_EXPLANATION);
        return promotionDistribution;
    }

    @BeforeEach
    public void initTest() {
        promotionDistribution = createEntity(em);
    }

    @Test
    @Transactional
    public void createPromotionDistribution() throws Exception {
        int databaseSizeBeforeCreate = promotionDistributionRepository.findAll().size();
        // Create the PromotionDistribution
        restPromotionDistributionMockMvc.perform(post("/api/promotion-distributions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(promotionDistribution)))
            .andExpect(status().isCreated());

        // Validate the PromotionDistribution in the database
        List<PromotionDistribution> promotionDistributionList = promotionDistributionRepository.findAll();
        assertThat(promotionDistributionList).hasSize(databaseSizeBeforeCreate + 1);
        PromotionDistribution testPromotionDistribution = promotionDistributionList.get(promotionDistributionList.size() - 1);
        assertThat(testPromotionDistribution.getPromotionDistributionId()).isEqualTo(DEFAULT_PROMOTION_DISTRIBUTION_ID);
        assertThat(testPromotionDistribution.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPromotionDistribution.getExplanation()).isEqualTo(DEFAULT_EXPLANATION);
    }

    @Test
    @Transactional
    public void createPromotionDistributionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = promotionDistributionRepository.findAll().size();

        // Create the PromotionDistribution with an existing ID
        promotionDistribution.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPromotionDistributionMockMvc.perform(post("/api/promotion-distributions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(promotionDistribution)))
            .andExpect(status().isBadRequest());

        // Validate the PromotionDistribution in the database
        List<PromotionDistribution> promotionDistributionList = promotionDistributionRepository.findAll();
        assertThat(promotionDistributionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPromotionDistributions() throws Exception {
        // Initialize the database
        promotionDistributionRepository.saveAndFlush(promotionDistribution);

        // Get all the promotionDistributionList
        restPromotionDistributionMockMvc.perform(get("/api/promotion-distributions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(promotionDistribution.getId().intValue())))
            .andExpect(jsonPath("$.[*].promotionDistributionId").value(hasItem(DEFAULT_PROMOTION_DISTRIBUTION_ID)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].explanation").value(hasItem(DEFAULT_EXPLANATION)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllPromotionDistributionsWithEagerRelationshipsIsEnabled() throws Exception {
        when(promotionDistributionRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restPromotionDistributionMockMvc.perform(get("/api/promotion-distributions?eagerload=true"))
            .andExpect(status().isOk());

        verify(promotionDistributionRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllPromotionDistributionsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(promotionDistributionRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restPromotionDistributionMockMvc.perform(get("/api/promotion-distributions?eagerload=true"))
            .andExpect(status().isOk());

        verify(promotionDistributionRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getPromotionDistribution() throws Exception {
        // Initialize the database
        promotionDistributionRepository.saveAndFlush(promotionDistribution);

        // Get the promotionDistribution
        restPromotionDistributionMockMvc.perform(get("/api/promotion-distributions/{id}", promotionDistribution.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(promotionDistribution.getId().intValue()))
            .andExpect(jsonPath("$.promotionDistributionId").value(DEFAULT_PROMOTION_DISTRIBUTION_ID))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.explanation").value(DEFAULT_EXPLANATION));
    }
    @Test
    @Transactional
    public void getNonExistingPromotionDistribution() throws Exception {
        // Get the promotionDistribution
        restPromotionDistributionMockMvc.perform(get("/api/promotion-distributions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePromotionDistribution() throws Exception {
        // Initialize the database
        promotionDistributionRepository.saveAndFlush(promotionDistribution);

        int databaseSizeBeforeUpdate = promotionDistributionRepository.findAll().size();

        // Update the promotionDistribution
        PromotionDistribution updatedPromotionDistribution = promotionDistributionRepository.findById(promotionDistribution.getId()).get();
        // Disconnect from session so that the updates on updatedPromotionDistribution are not directly saved in db
        em.detach(updatedPromotionDistribution);
        updatedPromotionDistribution
            .promotionDistributionId(UPDATED_PROMOTION_DISTRIBUTION_ID)
            .name(UPDATED_NAME)
            .explanation(UPDATED_EXPLANATION);

        restPromotionDistributionMockMvc.perform(put("/api/promotion-distributions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPromotionDistribution)))
            .andExpect(status().isOk());

        // Validate the PromotionDistribution in the database
        List<PromotionDistribution> promotionDistributionList = promotionDistributionRepository.findAll();
        assertThat(promotionDistributionList).hasSize(databaseSizeBeforeUpdate);
        PromotionDistribution testPromotionDistribution = promotionDistributionList.get(promotionDistributionList.size() - 1);
        assertThat(testPromotionDistribution.getPromotionDistributionId()).isEqualTo(UPDATED_PROMOTION_DISTRIBUTION_ID);
        assertThat(testPromotionDistribution.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPromotionDistribution.getExplanation()).isEqualTo(UPDATED_EXPLANATION);
    }

    @Test
    @Transactional
    public void updateNonExistingPromotionDistribution() throws Exception {
        int databaseSizeBeforeUpdate = promotionDistributionRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPromotionDistributionMockMvc.perform(put("/api/promotion-distributions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(promotionDistribution)))
            .andExpect(status().isBadRequest());

        // Validate the PromotionDistribution in the database
        List<PromotionDistribution> promotionDistributionList = promotionDistributionRepository.findAll();
        assertThat(promotionDistributionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePromotionDistribution() throws Exception {
        // Initialize the database
        promotionDistributionRepository.saveAndFlush(promotionDistribution);

        int databaseSizeBeforeDelete = promotionDistributionRepository.findAll().size();

        // Delete the promotionDistribution
        restPromotionDistributionMockMvc.perform(delete("/api/promotion-distributions/{id}", promotionDistribution.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PromotionDistribution> promotionDistributionList = promotionDistributionRepository.findAll();
        assertThat(promotionDistributionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

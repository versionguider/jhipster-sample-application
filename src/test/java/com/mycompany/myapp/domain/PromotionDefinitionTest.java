package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class PromotionDefinitionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PromotionDefinition.class);
        PromotionDefinition promotionDefinition1 = new PromotionDefinition();
        promotionDefinition1.setId(1L);
        PromotionDefinition promotionDefinition2 = new PromotionDefinition();
        promotionDefinition2.setId(promotionDefinition1.getId());
        assertThat(promotionDefinition1).isEqualTo(promotionDefinition2);
        promotionDefinition2.setId(2L);
        assertThat(promotionDefinition1).isNotEqualTo(promotionDefinition2);
        promotionDefinition1.setId(null);
        assertThat(promotionDefinition1).isNotEqualTo(promotionDefinition2);
    }
}

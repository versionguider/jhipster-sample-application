package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class PromotionDistributionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PromotionDistribution.class);
        PromotionDistribution promotionDistribution1 = new PromotionDistribution();
        promotionDistribution1.setId(1L);
        PromotionDistribution promotionDistribution2 = new PromotionDistribution();
        promotionDistribution2.setId(promotionDistribution1.getId());
        assertThat(promotionDistribution1).isEqualTo(promotionDistribution2);
        promotionDistribution2.setId(2L);
        assertThat(promotionDistribution1).isNotEqualTo(promotionDistribution2);
        promotionDistribution1.setId(null);
        assertThat(promotionDistribution1).isNotEqualTo(promotionDistribution2);
    }
}

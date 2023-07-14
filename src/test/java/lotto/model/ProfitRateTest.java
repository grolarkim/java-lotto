package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

class ProfitRateTest {

    @Test
    void 로또_수익률_객체_생성_및_내부값_정상() {
        // given, when, then
        assertThat(new ProfitRate(10000, 1000).getProfitRate()).isCloseTo(10.0,
                Percentage.withPercentage(99.9));
    }

    @Test
    void 로또_수익률_총상금_음수일시_예외발생() {
        // given, when, then
        assertThrows(IllegalArgumentException.class, () -> new ProfitRate(-1L, 1000));
    }

    @Test
    void 로또_수익률_지불한돈_1000이하_일시_예외발생() {
        // given, when, then
        assertThrows(IllegalArgumentException.class, () -> new ProfitRate(0, -1));
    }
}

package lotto.model;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class LottoResult {

    private static final long DEFAULT_VALUE = 0L;

    private final Map<Rank, Long> lottoResultStatistics;
    private final ProfitRate profitRate;

    public LottoResult(final RankResultsDto ranks, final LottoMoney lottoMoney) {
        this.lottoResultStatistics = ranks.getRanks()
                .stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()));
        this.profitRate = calculateProfitRate(lottoMoney);
    }

    private ProfitRate calculateProfitRate(final LottoMoney lottoMoney) {
        long totalPrize = lottoResultStatistics.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getTotalPrize(entry.getValue()))
                .sum();
        return new ProfitRate(totalPrize, lottoMoney.getTotalSpentMoney());
    }

    public long getCount(final Rank rank) {
        return lottoResultStatistics.getOrDefault(rank, DEFAULT_VALUE);
    }

    public double getProfitRate() {
        return profitRate.getProfitRate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoResult that = (LottoResult) o;
        return Objects.equals(lottoResultStatistics, that.lottoResultStatistics)
                && Objects.equals(profitRate, that.profitRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoResultStatistics, profitRate);
    }
}

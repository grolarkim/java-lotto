package lotto.model.service;

import lotto.model.domain.LottoMoney;
import lotto.model.domain.LottoResult;
import lotto.model.domain.Lottos;
import lotto.model.domain.NumbersGenerator;
import lotto.model.domain.RankResults;
import lotto.model.domain.WinningNumbers;

public final class LottoService {

    private final Lottos manualLottos;
    private final Lottos autoLottos;
    private final LottoMoney lottoMoney;

    public LottoService(final LottoMoney lottoMoney, Lottos manualLottos,
            final NumbersGenerator numbersGenerator) {
        this.lottoMoney = lottoMoney;
        this.autoLottos = new Lottos(lottoMoney.getAutoCount(), numbersGenerator);
        this.manualLottos = manualLottos;
    }

    public Lottos getAutoLottos() {
        return this.autoLottos;
    }

    public LottoMoney getLottoMoney() {
        return this.lottoMoney;
    }

    public int getChange() {
        return this.lottoMoney.getChange();
    }

    public LottoResult calculateLottoResult(final WinningNumbers winningNumbers) {
        RankResults totalRankResults = new RankResults(
                manualLottos.matchWinningNumbers(winningNumbers),
                autoLottos.matchWinningNumbers(winningNumbers));
        return new LottoResult(totalRankResults, lottoMoney);
    }
}
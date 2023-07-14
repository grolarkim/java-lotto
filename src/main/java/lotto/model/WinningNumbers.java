package lotto.model;

public final class WinningNumbers {

    private final Lotto winningNumbers;
    private final LottoNumber bonusBall;

    public WinningNumbers(final Lotto winningNumbers, final LottoNumber bonusBall) {
        validateDistinctionWithBonusBall(winningNumbers, bonusBall);

        this.winningNumbers = winningNumbers;
        this.bonusBall = bonusBall;
    }

    private void validateDistinctionWithBonusBall(Lotto winningNumbers, LottoNumber bonusBall) {
        if (winningNumbers.hasNumber(bonusBall)) {
            throw new IllegalArgumentException("당첨 번호와 보너스 볼은 중복될 수 없습니다.");
        }
    }

    public Rank checkRank(final Lotto lotto) {
        final int matchCount = lotto.checkMatchCount(winningNumbers);
        final boolean hasBonusBall = lotto.hasNumber(bonusBall);
        return Rank.valueOf(matchCount, hasBonusBall);
    }
}

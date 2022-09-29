package me.lotto.analyser.repository;

import me.lotto.analyser.vo.LottoRound;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LottoRoundRepository extends JpaRepository<LottoRound, Integer> {
    LottoRound findFirstBySyncYnOrderByRoundDesc(String syncYn);

}

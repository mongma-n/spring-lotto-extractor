package me.lotto.analyser.service;

import lombok.extern.slf4j.Slf4j;
import me.lotto.analyser.repository.LottoRepository;
import me.lotto.analyser.repository.LottoRoundRepository;
import me.lotto.analyser.vo.LottoRound;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LottoServiceImpl implements LottoService {

    private final LottoRepository lottoRepository;
    private final LottoRoundRepository lottoRoundRepository;

    public LottoServiceImpl(LottoRepository lottoRepository, LottoRoundRepository lottoRoundRepository) {
        this.lottoRepository = lottoRepository;
        this.lottoRoundRepository = lottoRoundRepository;
    }

    @Override
    public int syncLottoByRound(int round) {
        // 1. Meta 정보 Round 별 동기화 여부 조회 (Meta 없으면 Insert 1 Round)
        LottoRound lottoRound = lottoRoundRepository.findFirstBySyncYnOrderByRoundDesc("Y");
        if (lottoRound == null) {
            log.info("Round Meta is empty.");
            lottoRound = new LottoRound();
            lottoRound.setRound(1);
            lottoRound.setSyncYn("N");
            lottoRoundRepository.save(lottoRound);
        }

        // Round Sync Loop
        // 2. 가장 마지막 Round + 1 회부터 Input Round 까지 Sync
        do {
            // Round 별 추출
            // https://getNumber

            // Round 별 6 Number Loop
            // 2-1. lottoRepository save (count 는 JPA 로 + 1 방법)


            // 2-2. Meta Round 업데이트
            lottoRound.setSyncYn("Y");
            lottoRoundRepository.save(lottoRound);


            lottoRound.setRound(round++);
            // 3. Input Round 까지 동기화 수행되면 Break;
        } while (round == lottoRound.getRound());



        return 0;
    }

    @Override
    public int extractNumber() {
        return 0;
    }
}

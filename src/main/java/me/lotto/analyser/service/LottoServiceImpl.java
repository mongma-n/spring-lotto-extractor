package me.lotto.analyser.service;

import lombok.extern.slf4j.Slf4j;
import me.lotto.analyser.repository.LottoRepository;
import me.lotto.analyser.repository.LottoRoundRepository;
import me.lotto.analyser.vo.LottoRound;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
//        for (int i = 0; i < 5; i++) {
//            Set<Integer> randomSet = new HashSet<>();
//            getFixedThreeNumberAndRemove(randomSet);
//            while (true) {
//                randomSet.add(getOtherNum());
//                if (randomSet.size() == 6) break;
//            }
//            System.out.println(randomSet);
//        }

//        List<Integer> randoms = new ArrayList<>();
//        randoms.add(21);
//        randoms.add(31);
//        randoms.add(45);
//        randoms.add(25);
//        randoms.add(5);
//        randoms.add(30);
//        randoms.add(29);
//        randoms.add(41);
//        randoms.add(23);
//        return randoms.get(random.nextInt(randoms.size()));



//        String response = null;
//        LotResult lotResult = null;
//        Gson gson = new Gson();
//        Set<Integer> removeNums = new HashSet<>();
//
//        for (int i = 0; i < 3; i++) {
//            response = IOUtils.toString(new URL("https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=" + (round - i)), StandardCharsets.UTF_8);
//            lotResult = gson.fromJson(response, LotResult.class);
//            removeNums.add(lotResult.getDrwtNo1());
//            removeNums.add(lotResult.getDrwtNo2());
//            removeNums.add(lotResult.getDrwtNo3());
//            removeNums.add(lotResult.getDrwtNo4());
//            removeNums.add(lotResult.getDrwtNo5());
//            removeNums.add(lotResult.getDrwtNo6());
//            removeNums.add(lotResult.getBnusNo());
//        }
//        System.out.println("Remove Numbers :: " + removeNums);
//
//        for (int i : removeNums) {
//            target.remove(Integer.valueOf(i));
//        }
        return 0;
    }
}

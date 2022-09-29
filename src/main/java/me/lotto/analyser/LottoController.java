package me.lotto.analyser;

import me.lotto.analyser.repository.LottoRepository;
import me.lotto.analyser.service.LottoService;
import me.lotto.analyser.vo.LottoNumber;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/lotto")
public class LottoController {

    private final LottoRepository lottoRepository;
    private final LottoService lottoService;

    public LottoController(LottoService lottoService, LottoRepository lottoRepository) {
        this.lottoService = lottoService;
        this.lottoRepository = lottoRepository;
    }

    @GetMapping(path = "/v1/numbers")
    public @ResponseBody Iterable<LottoNumber> getAllNumbers() {
        lottoService.syncLottoByRound(1);
        return lottoRepository.findAll();
    }

    @PostMapping(path = "/v1/add")
    public @ResponseBody String addLottoNumber(@RequestParam int round
                    , @RequestParam int lottoNumber, @RequestParam int appearCount) {

        LottoNumber lotto = new LottoNumber();
        lotto.setNumber(lottoNumber);
        lotto.setAppearCount(appearCount);
        lotto.setRound(round);

        lottoRepository.save(lotto);
        return "Success.";
    }

}

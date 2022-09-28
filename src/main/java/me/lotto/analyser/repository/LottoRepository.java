package me.lotto.analyser.repository;

import me.lotto.analyser.vo.LottoNumber;
import org.springframework.data.repository.CrudRepository;

public interface LottoRepository extends CrudRepository<LottoNumber, Integer> {

}

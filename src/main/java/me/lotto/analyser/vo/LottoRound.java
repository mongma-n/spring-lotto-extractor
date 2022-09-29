package me.lotto.analyser.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class LottoRound {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int round;
    private String syncYn;

}

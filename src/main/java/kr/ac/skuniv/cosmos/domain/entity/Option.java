package kr.ac.skuniv.cosmos.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Option {

    public String[] speakerList;    //발화자 리스트
    public String stringOption = "0000000"; // 이건 역할이 뭐지?

}

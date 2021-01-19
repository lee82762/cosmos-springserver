package kr.ac.skuniv.cosmos.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KDataVer2 {

    private String uid;
    private String speaker;
    private String text;
    private String time;
}

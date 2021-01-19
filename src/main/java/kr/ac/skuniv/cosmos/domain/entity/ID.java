package kr.ac.skuniv.cosmos.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ID {

    /**
     *  Header 클래스의 멤버변수 중 arrID라는 이름의 List<ID> 변수를 확인할 수 있다.
     *  발화자의 정보를 List 형식으로 저장한 것이다.
     */

    /**
     * Corpus : 말뭉치 정보(Nazarene 등)
     * Code : 역할 코드(CHI, MOT 등)
     * DateOfBirth : 생년월일(ex : 09-FEB-2020)
     * Age : 나이 정보(ex : 26 또는 20대)
     * Sex : 성별(남성 : H, 여성 : F)
     * Group : 그룹(도시규모 - 대도시, 중소도시, 농어촌)
     * Region : 지역(서울/경기, 경상도, 전라도 등)
     * SES : 경제적 지위(150만원 이하, 151-250만원 등)
     * Edu : 학력(초등학교 졸업 이하, 대학교 졸업 등)
     * Role : 역할(대상 아동, 엄마, 아빠 등)
     */
    private String Corpus = "";
    private String Code = "";
    private String DateOfBirth = "";
    private String Age = "";
    private String Sex = "";
    private String Group = "";
    private String Region = "";
    private String SES = "";
    private String Edu = "";
    private String Role = "";
}

package kr.ac.skuniv.cosmos.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Header {

    /**
     * 프로젝트를 생성할 때 헤더마법사를 통해 미디어 파일과 전사 작업에 대한 정보를 헤더로 기록한다.
     * 헤더는 해당 프로젝트를 파일로 저장할 때 <Header> 라는 태그로 기록이 된다.
     */

    /**
     * speechType : 언어 종류
     * arrParticipants : 구성원 리스트
     * arrID : 발화자 정보
     * Language : 언어
     * BirthOfCHI : 아동의 생년월일
     * BirthPlaceOfCHI : 아동의 출생지
     * Date : 녹음/녹화 날짜
     * Location : 장소
     * Situation : 상황
     * Media : 미디어 파일명, 종류 정보
     * Recording : Recording Quality(음질)
     * Transcriber : 전사자
     * Reviewer : 검토자
     * Comment : 코멘트(헤더마법사)
     */
    private String speechType;
    private String[] arrParticipants;
    private List<ID> arrID;
    private String language = "";
    private String birthOfCHI = "";
    private String birthPlaceOfCHI = "";
    private String date = "";
    private String location = "";
    private String situation = "";
    private String media = "";
    private String recording = "";
    private String transcriber = "";
    private String reviewer = "";
    private String comment = "";
}

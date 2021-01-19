package kr.ac.skuniv.cosmos.domain.entity.nouse;

import java.util.List;

public class Data {

    /**
     * CosmoScribe 프로젝트 파일에서 <Data> 태그 아래에 기록되는 정보들을 저장하는 멤버변수를 가지는 클래스
     */

    /**
     * DataType : CosmoScribe 프로젝트 파일에서 해당 Data가 발화(Utterance)Data 인지
     *            주석(Comment) Data 인지 구별하기 위한 멤버변수 <DataType> 태그에서 사용된다.
     * data1 : CosmoScribe 프로젝트 파일에서 해당 Data의 발하자 역할 코드가 들어가는 멤버변수 <Speaker> 태그에서 사용된다.
     * ST : CosmoScribe 프로젝트 파일에서 해당 Data의 레이블링 시작 시간을 기록하기 위한 멤버변수 <TimePosition> 태그 내에 첫 번째 값으로 사용 된다.
     * ET : CosmoScribe 프로젝트 파일에서 해당 Data의 레이블링 끝 시간을 기록하기 위한 멤버변수 <TimePosition> 태그 내에 두 번째 값으로 사용 된다.
     * datas : CosmoScribe 프로젝트 파일에서 <Tier> 태그들의 정보를 기록하는 Tier 클래스의 List 멤버변수
     */
    public DataType type;
    public String data1 = "";   // == speaker = 발화자 데이터
    public double ST = -1;
    public double ET = -1;
    public List<Tier> datas;
}

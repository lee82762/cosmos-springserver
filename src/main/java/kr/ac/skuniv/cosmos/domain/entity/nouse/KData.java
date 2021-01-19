package kr.ac.skuniv.cosmos.domain.entity.nouse;

import java.util.List;

public class KData {

    /**
     * KStars 프로젝트 파일에서 <Data> 태그 아래에 기록되는 정보들을 저장하는 멤버변수를 가진 클래스
     */

    /**
     * type : KStars 프로젝트 파일에서 해당 Data의 타입을 저장하는 멤버변수
     *        KStars 에서는 KTier 클래스에서 타입을 구별하기 때문에 KData로 값이 고정된다.
     * speaker : KStars 프로젝트 파일에서 발화자의 역할 코드를 기록하는 멤버변수 <Speaker> 태그에서 사용된다.
     * datas : KStars 프로젝트 파일에서 <Tier> 태그들의 정보를 기록하는 KTier 클래스의 List 멤버변수
     */
    private DataType type;
    public String speaker;
    public List<KTier> datas;
}

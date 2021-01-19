package kr.ac.skuniv.cosmos.domain.entity.nouse;

public enum DataType {
    /**
     * Empty : 아직 미 설정
     * Comment : 주석, 코멘트
     * Utterance : 발화정보
     * KData : KStars 용 DataType, 위의 Comment, Utterance를 통합
     */
    Empty, Comment, Utterance, KData
}

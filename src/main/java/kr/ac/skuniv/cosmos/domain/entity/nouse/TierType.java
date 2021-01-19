package kr.ac.skuniv.cosmos.domain.entity.nouse;

public enum TierType {

    /**
     * Empty : 아직 사용되지 않음
     * ** CosmoScribe
     * Comment : 주석
     * Text : 전사내용
     * Morpheme : 형태소 분석 결과
     * ** KStars
     * KComment : 주석
     * KUtterance : 전사 내용
     * KMorpheme : 형태소 분석 결과
     */
    Empty, Comment, Text, Morpheme, KComment, KUtterance, KMorpheme

}

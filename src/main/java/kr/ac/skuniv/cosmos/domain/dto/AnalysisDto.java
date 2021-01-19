package kr.ac.skuniv.cosmos.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnalysisDto {

    private Long brdno;             // 형태소 분석할 문장 번호
    private String analysisType;    // 타입 Morp, ConLpy
    private String talker;          // 누가 말한건지 ex) 아기, 엄마, 아빠
    private String text;            // 분석할 문장
    private String analysisResult;  // 분석된 문장 ex ) ""

}

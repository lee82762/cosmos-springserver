package kr.ac.skuniv.cosmos.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisResultDto {
    private List<String> key;
    private List<String> value;
}


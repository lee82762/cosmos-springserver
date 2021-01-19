package kr.ac.skuniv.cosmos.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Audio {

    public String[] audioPath;  //mp3파일 저장 되어있는 경로
    public int audioFileIndex;      // ??
    public double audioCurrentPosition; // ??

}

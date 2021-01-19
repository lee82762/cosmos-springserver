package kr.ac.skuniv.cosmos.domain.entity;

import kr.ac.skuniv.cosmos.domain.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KSTProject {

    private String version;
    private Option m_Option;
    private Header m_header;
    private KTierVer2 m_KTierVer2;
    private KTierMorpVer2 m_KTierMorpVer2;
    private Audio m_Audio;
    private UserDto userDto;

//    private List<Data> m_data;
//    private List<KData> m_KData;
//    private KFilePath m_KFilePath;
//    public List<String> m_arrCustom;
//    public List<String> m_arrCustom_cn;
//    public List<UndoData> m_undoData;
//    public int UndoCount;

}

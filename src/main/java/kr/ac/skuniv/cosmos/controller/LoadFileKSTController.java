package kr.ac.skuniv.cosmos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ac.skuniv.cosmos.domain.dto.TokenDto;
import kr.ac.skuniv.cosmos.domain.dto.UserDto;
import kr.ac.skuniv.cosmos.domain.entity.*;
import kr.ac.skuniv.cosmos.service.LoadFileKSTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * kst파일 불러오기
 */
@RestController
@RequestMapping("/cosmos/kStars")
public class LoadFileKSTController {

    @Autowired
    private LoadFileKSTService loadFileKSTService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/load/kst")
    public void loadFileKST(@RequestBody UserDto userDto) throws Exception {
        List<String> list = loadFileKSTService.loadFileList(userDto);
        for(int i=0; i<list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    @PostMapping("/load/kst/token")
    public List<String> loadFileKSTByToken(@RequestBody TokenDto tokenDto) throws Exception {
        List<String> list = loadFileKSTService.loadFileListByToken(tokenDto.getToken());
        for(int i=0; i<list.size(); i++) {
            System.out.println(list.get(i));
        }
        return list;
    }

    @PostMapping("load/kst/localFile")
    public void loadLocalFileAndSave(@RequestParam("file") MultipartFile multipartFile,
                                     @RequestParam String json) throws IOException {
        UserDto userDto = objectMapper.readValue(json, UserDto.class);
        loadFileKSTService.loadLocalFileAndSave(multipartFile, userDto);
    }

    @PostMapping("load/kst/serverFile")
    public void loadServerFileAndSave(@RequestBody UserDto userDto) {
        loadFileKSTService.loadServerFileAndSave(userDto);
    }

    @PostMapping("load/kst/data")
    public void loadFileData() {
        KSTProject kstProject = new KSTProject();
        Option option = new Option();
        String[] speakerList = new String[]{"string"};
        option.setSpeakerList(speakerList);

        Header header = new Header();
        String[] arrParticipants = new String[]{"string"};
        header.setArrParticipants(arrParticipants);
        ID id = new ID(
                "string", "string","string",
                "string","string","string","string",
                "string","string","string");
        List<ID> ids = new ArrayList<>();
        ids.add(id);
        header.setArrID(ids);

        KTierVer2 kTierVer2 = new KTierVer2();
        KDataVer2 kDataVer2 = new KDataVer2(
                "string", "string", "string", "string"
        );
        List<KDataVer2> kDataVer2s = new ArrayList<>();
        kDataVer2s.add(kDataVer2);
        kTierVer2.setDatas(kDataVer2s);

        KTierMorpVer2 kTierMorpVer2 = new KTierMorpVer2();
        KMorpVer2 kMorpVer2 = new KMorpVer2(
                "string", "string", "string", "string"
        );
        List<KMorpVer2> kMorpVer2s = new ArrayList<>();
        kMorpVer2s.add(kMorpVer2);
        kTierMorpVer2.setDatas(kMorpVer2s);

        Audio audio = new Audio();
        String[] audioPath = new String[]{"string"};
        audio.setAudioPath(audioPath);

        kstProject.setM_Option(option);
        kstProject.setM_header(header);
        kstProject.setM_KTierVer2(kTierVer2);
        kstProject.setM_KTierMorpVer2(kTierMorpVer2);
        kstProject.setM_Audio(audio);

        loadFileKSTService.loadFileData(kstProject);
    }
}

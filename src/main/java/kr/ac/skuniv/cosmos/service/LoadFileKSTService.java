package kr.ac.skuniv.cosmos.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ac.skuniv.cosmos.domain.dto.UserDto;
import kr.ac.skuniv.cosmos.domain.entity.KSTProject;
import kr.ac.skuniv.cosmos.domain.entity.member.Member;
import kr.ac.skuniv.cosmos.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoadFileKSTService {

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    private JwtService jwtService;

//    public static String absolutePath = "C:\\Users\\User\\eclipse-workspace\\K-Stars\\src\\main\\java\\kr\\ac\\skuniv\\cosmos";
    public static String cloudAbsolutePath = "/home/ubuntu/kst";


    public List<String> loadFileList(UserDto userDto) throws Exception {

        if(userDto.getUser().equals("guest")) {
            throw new Exception("Guest Error");
        }

//        File path = new File(absolutePath + "\\user\\" + userDto.getId());


            File path = new File(cloudAbsolutePath + "/user/" + userDto.getId());
            File[] files = path.listFiles();
            List<String> fileName = new ArrayList<>();
            try {
                for(int i=0; i<files.length; i++) {
                    File file = files[i];
                    if(file.isFile()) {
                        fileName.add(file.getName());
                    }
                }
            } catch (Exception e) {

        }

        return fileName;
    }

    public List<String> loadFileListByToken(String token) throws Exception {

        Member member = jwtService.findMemberByToken(token);

        if(!member.getRole().equals("USER"))
            throw new Exception("Guest Error");

//        File path = new File(absolutePath + "\\user\\" + member.getEmail());
        File path = new File(cloudAbsolutePath + "/user/" + member.getEmail());
        System.out.println(path);
        File[] files = path.listFiles();
        List<String> fileName = new ArrayList<>();
        try {
            for(int i=0; i<files.length; i++) {
                File file = files[i];
                if(file.isFile()) {
                    fileName.add(file.getName());
                }
            }
        } catch (Exception e) {

        }

        for(int i=0; i<files.length; i++) {
            System.out.println(fileName.get(i));
        }

        return fileName;
    }

    public void loadLocalFileAndSave(MultipartFile multipartFile, UserDto userDto) throws IOException {

//        String absolutePath = "C:/Users/User/eclipse-workspace/K-Stars/src/main/java/kr/ac/skuniv/cosmos";
        String cloudAbsolutePath = "/home/ubuntu/kst";

        if(userDto.getUser().equals("user")) {
//            multipartFile.transferTo(new File(absolutePath + "\\user\\temp\\" + multipartFile.getOriginalFilename()));
            multipartFile.transferTo(new File(cloudAbsolutePath + "/user/temp/" + multipartFile.getOriginalFilename()));
        }
        else if(userDto.getUser().equals("guest")) {
//            multipartFile.transferTo(new File(absolutePath + "\\guest\\temp" + multipartFile.getOriginalFilename()));
            multipartFile.transferTo(new File(cloudAbsolutePath + "/guest/temp" + multipartFile.getOriginalFilename()));
        }
    }

    public void loadServerFileAndSave(UserDto userDto){

//        File path = new File(absolutePath + "\\user\\" + userDto.getId());
        File path = new File(cloudAbsolutePath + "/user/" + userDto.getId());
        File[] files = path.listFiles();

        try {
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                if (file.isFile()) {
                    if (file.getName().equals(userDto.getFileName())) {

//                        File originFilePath = new File(absolutePath + "\\user\\" + userDto.getId() + "\\" + file.getName());
//                        File copyFilePath = new File(absolutePath + "\\user\\temp\\" + file.getName());

                        File originFilePath = new File(cloudAbsolutePath + "/user/" + userDto.getId() + "\\" + file.getName());
                        File copyFilePath = new File(cloudAbsolutePath + "/user/temp/" + file.getName());

                        FileInputStream fileInputStream = null;
                        FileOutputStream fileOutputStream = null;
                        try {
                            fileInputStream = new FileInputStream(originFilePath);
                            fileOutputStream = new FileOutputStream(copyFilePath);
                            int fileByte = 0;

                            while ((fileByte = fileInputStream.read()) != -1) {
                                fileOutputStream.write(fileByte);
                            }

                        } catch (FileNotFoundException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } finally {
                            fileInputStream.close();
                            fileOutputStream.close();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadFileData(KSTProject kstProject) {
//        KSTProject result = restTemplate.postForObject("http://localhost:5000/cosmos/KStars/load/kst", kstProject, KSTProject.class);
        KSTProject result = restTemplate.postForObject("http://18.221.109.14:5000/cosmos/KStars/load/kst", kstProject, KSTProject.class);
        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println(result.getVersion());
        System.out.println(result.getM_Option().getStringOption());
        for(int i=0; i<result.getM_Option().getSpeakerList().length; i++) {
            System.out.println(result.getM_Option().getSpeakerList()[i]);
        }

    }
}

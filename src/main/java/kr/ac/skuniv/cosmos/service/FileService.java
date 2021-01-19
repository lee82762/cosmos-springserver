package kr.ac.skuniv.cosmos.service;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

@Service
public class FileService {

    private static final String FILE_PATH = "resources.file-locations";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    public String saveFile(MultipartFile file) throws IOException {

        UUID uid = UUID.randomUUID();
        String fileName = uid + "_" + file.getOriginalFilename();
        System.out.println(FILE_PATH);

        String savePath = makePath(environment.getProperty(FILE_PATH));
        File destinationFile = new File(environment.getProperty(FILE_PATH) + savePath, fileName);

        file.transferTo(destinationFile);

        String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/kStars/file/")
                .path(fileName)
                .toUriString();

        String path = environment.getProperty(FILE_PATH) + savePath + File.separator + fileName;
        System.out.println(path);
        System.out.println(fileUrl);
        return path;
    }

    private String makePath(String uploadPath) {

        Calendar calendar = Calendar.getInstance();

        String yearPath = File.separator + calendar.get(Calendar.YEAR);
        String monthPath = yearPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.MONTH)+1);
        String datePath = monthPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.DATE));


        makeDir(uploadPath, yearPath, monthPath, datePath);

        return datePath;

    }

    private void makeDir(String uploadPath, String... paths) {

        if(new File(uploadPath + paths[paths.length - 1]).exists()) {
            return;
        }

        for( String path : paths){
            File dirPath = new File(uploadPath + path);
            if(!dirPath.exists()){
                dirPath.mkdir();
            }
        }

    }

    public byte[] getFileResource(String url) {

        byte[] result = null;
        try{
            File file = new File(url);

            InputStream in = new FileInputStream(file);
            result = IOUtils.toByteArray(in);

            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}

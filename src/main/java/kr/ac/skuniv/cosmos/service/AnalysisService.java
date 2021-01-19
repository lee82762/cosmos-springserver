package kr.ac.skuniv.cosmos.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ac.skuniv.cosmos.domain.dto.AnalysisDto;
import kr.ac.skuniv.cosmos.domain.dto.AnalysisResultDto;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AnalysisService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AnalysisService.class);

    @Autowired
    private RestTemplate restTemplate;

    public AnalysisDto analysisTest(AnalysisDto analysisDto) {

        AnalysisDto result = restTemplate.postForObject("http://18.221.109.14:5000/test", analysisDto, AnalysisDto.class);

        logger.info(result.getAnalysisResult());


//        ObjectMapper objectMapper = new ObjectMapper();
//
//        try {
//            AnalysisResultDto analysisResultDto = objectMapper.readValue(result.getAnalysisResult(), AnalysisResultDto.class);
//
//            String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(analysisResultDto);
//
//            logger.info(jsonString);
//
//        } catch (JsonMappingException e) {
//            e.printStackTrace();
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }

        return result;
    }

    public AnalysisDto analysisTest2(AnalysisDto analysisDto) {


        if(analysisDto.getAnalysisType().equals("wsdAPI")) {

//            AnalysisDto result = restTemplate.postForObject("http://localhost:5000/cosmos/KStars/morp", analysisDto, AnalysisDto.class);
            AnalysisDto result = restTemplate.postForObject("http://18.221.109.14:5000/cosmos/KStars/morp", analysisDto, AnalysisDto.class);

            ObjectMapper objectMapper = new ObjectMapper();

            try {
                AnalysisResultDto analysisResultDto = objectMapper.readValue(result.getAnalysisResult(), AnalysisResultDto.class);

                String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(analysisResultDto);

                logger.info(jsonString);

            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            logger.info("Result : " + result.getAnalysisResult());

            return result;
        }
        else if(analysisDto.getAnalysisType().equals("morpAPI")){

            AnalysisDto result = restTemplate.postForObject("http://18.221.109.14:5000/cosmos/KStars/morp", analysisDto, AnalysisDto.class);

            ObjectMapper objectMapper = new ObjectMapper();

            try {
                AnalysisResultDto analysisResultDto = objectMapper.readValue(result.getAnalysisResult(), AnalysisResultDto.class);

                String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(analysisResultDto);

                logger.info(jsonString);

            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            logger.info("Result : " + result.getAnalysisResult());

            return result;
        }

        return null;
    }

    public AnalysisDto reactTest(AnalysisDto analysisDto) {

        System.out.println(analysisDto.getTalker());
        System.out.println("");

        return AnalysisDto.builder()
                .talker(analysisDto.getTalker())
                .analysisType(analysisDto.getAnalysisType())
                .text(analysisDto.getText())
                .build();

    }

    public List<AnalysisDto> analysisListService(List<AnalysisDto> analysisDtos) {

//        List<AnalysisDto> result = restTemplate.postForObject("http://localhost:5000/cosmos/KStars/morpList", analysisDtos, List.class);
        List<AnalysisDto> result = restTemplate.postForObject("http://18.221.109.14:5000/cosmos/KStars/morpList", analysisDtos, List.class);

        return result;
    }

    public List<AnalysisDto> analysisListTestService(List<AnalysisDto> analysisDtos) {

//        List<AnalysisDto> result = restTemplate.postForObject("http://localhost:5000/cosmos/KStars/morpList/KoNLPy", analysisDtos, List.class);
        List<AnalysisDto> result = restTemplate.postForObject("http://18.221.109.14:5000/cosmos/KStars/morpList/KoNLPy", analysisDtos, List.class);
        return result;
    }
}
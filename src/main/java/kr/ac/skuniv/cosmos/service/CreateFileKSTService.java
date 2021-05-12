package kr.ac.skuniv.cosmos.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ac.skuniv.cosmos.domain.entity.KSTProject;
import kr.ac.skuniv.cosmos.domain.entity.member.Member;
import kr.ac.skuniv.cosmos.exception.BusinessLogicException;
import kr.ac.skuniv.cosmos.exception.ErrorCodeType;
import kr.ac.skuniv.cosmos.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CreateFileKSTService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JwtService jwtService;

    public void createKSTFile(KSTProject kstProject) {

         //KSTProject result = restTemplate.postForObject("http://localhost:5000/cosmos/KStars/create/kst", kstProject, KSTProject.class);
          KSTProject result = restTemplate.postForObject("http://3.86.166.99:5000/cosmos/KStars/create/kst", kstProject, KSTProject.class);
         ObjectMapper objectMapper = new ObjectMapper();

        System.out.println(result.getM_Audio());

    }

    public void createKSTUserFile(String token, KSTProject kstProject) {

        Member member = jwtService.findMemberByToken(token);
        System.out.println(member.getEmail());
        System.out.println(member.getPassword());

        if(member == null) {
            throw new BusinessLogicException(ErrorCodeType.UNKNOWN);
        }

        kstProject.getUserDto().setId(member.getEmail());
        //KSTProject result = restTemplate.postForObject("http://localhost:5000/cosmos/KStars/create/kst", kstProject, KSTProject.class);
        KSTProject result = restTemplate.postForObject("http://3.86.166.99:5000/cosmos/KStars/create/kst", kstProject, KSTProject.class);
        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println(result);

    }

}

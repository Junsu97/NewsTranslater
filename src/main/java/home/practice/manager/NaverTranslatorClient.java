package home.practice.manager;
import home.practice.IBase.ITranslate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Component
public class NaverTranslatorClient implements ITranslate {
    private final RestTemplate restTemplate;

    @Value("${naver.clientId}")
    private String clientId;

    @Value("${naver.clientSecret}")
    private String clientSecret;

    public NaverTranslatorClient(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    public String translate(String text, String sourceLang,String targetLang){
        String apiUrl = "https://openapi.naver.com/v1/papago/n2mt";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("X-Naver-Client-Id",clientId);
        headers.set("X-Naver-Client-Secret",clientSecret);

        String requestBody = String.format("source=%s&target=%s&text=%s",
                sourceLang,targetLang,text);

        TranslationResponse response = restTemplate.postForObject(apiUrl,requestBody, TranslationResponse.class);
        if(response != null){
            return response.getMessage().getResult().getTranslatedText();
        }else{
            log.info("result가 null입니다.");
            return null;
        }
    }
}

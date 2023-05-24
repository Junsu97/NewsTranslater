package home.practice;

import home.practice.IBase.IOcrService;
import home.practice.dto.OcrDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class NewsTranslaterApplication implements CommandLineRunner {
    private final RestTemplate restTemplate;
    private final IOcrService ocrService;
    public static void main(String[] args) {

        SpringApplication.run(NewsTranslaterApplication.class, args);
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("자바 프로그래밍 시작");

        String filePath = "img";
        String fileName = "engsample.jpg";


        OcrDTO pDTO = new OcrDTO();

        pDTO.setFilePath(filePath);
        pDTO.setFileName(fileName);

        OcrDTO rDTO = ocrService.getReadforImageText(pDTO,restTemplate);

        String result = rDTO.getResult();

        log.info("\n인식된 문자열");
        log.info(result);
        log.info("\n파파고 번역중...");
        String translateResult = rDTO.getTranslateResult();
        log.info(translateResult);
        log.info("\n자바 프로그래밍 종료");
    }
}

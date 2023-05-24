package home.practice.impl;

import home.practice.IBase.IOcrService;
import home.practice.IBase.ITranslate;
import home.practice.dto.OcrDTO;
import home.practice.manager.NaverTranslatorClient;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class OcrService implements IOcrService {
    private final RestTemplate restTemplate;

    public OcrService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public OcrDTO getReadforImageText(OcrDTO pDTO,RestTemplate restTemplate) throws Exception {
        log.info(this.getClass().getName() + ".getReadforImageText start");

        ClassPathResource resource = new ClassPathResource(pDTO.getFilePath() + "/" + pDTO.getFileName());

        ITesseract instance = new Tesseract();
        ITranslate translate = new NaverTranslatorClient(restTemplate);

        instance.setDatapath(IOcrService.modelFile);

        instance.setLanguage("eng");

        String result = instance.doOCR(resource.getFile());


        String translatedText = translate.translate(result, "en", "ko");
        pDTO.setResult(result);
        pDTO.setTranslateResult(translatedText);

        log.info(this.getClass().getName() + ".getReadforImageText end");


        return pDTO;
    }
}

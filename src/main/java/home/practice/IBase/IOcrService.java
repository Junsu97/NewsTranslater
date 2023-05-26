package home.practice.IBase;

import home.practice.dto.OcrDTO;
import org.springframework.web.client.RestTemplate;

public interface IOcrService {
    String modelFile = "C:/model/tessdata";

    OcrDTO getReadforImageText(OcrDTO pDTO,RestTemplate restTemplate) throws Exception;
}

package home.practice.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OcrDTO {
    private String filePath;
    private String fileName;
    private String result;

    private String translateResult;
}

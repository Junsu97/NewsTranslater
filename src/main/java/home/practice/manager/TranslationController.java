package home.practice.manager;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TranslationController {
    private final NaverTranslatorClient translatorClient;

    public TranslationController(NaverTranslatorClient translatorClient){
        this.translatorClient = translatorClient;
    }

    @GetMapping("/translate")
    public String translate(@RequestParam("text") String text,
                                     @RequestParam("source") String sourceLang,
                                     @RequestParam("target") String targetLang){
        return translatorClient.translate(text, sourceLang, targetLang);
    }
}

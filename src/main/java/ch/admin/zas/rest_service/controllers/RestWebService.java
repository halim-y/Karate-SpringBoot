package ch.admin.zas.rest_service.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class RestWebService {
    @GetMapping(value="/hello", produces = "application/json")
    public String greet(@RequestParam(value="name", required = false, defaultValue = "World") String insName){
        return String.format("Hello %s!", insName);
    }

    @GetMapping(value="/hello/advanced", produces = "application/json")
    public String greetLang(@RequestParam(value="name", required = false, defaultValue = ":)") String insName,
                            @RequestParam(value="lang", required = false, defaultValue = "fr") String lang){
        String greeting = "";
        if (lang.equals("fr")){
            return String.format("Bonjour %s!", insName);
        } else if (lang.equals("en")) {
            return String.format("Hello %s!", insName);
        } else{
            return String.format("Error, this language doesn't exist !");
        }
    }
}


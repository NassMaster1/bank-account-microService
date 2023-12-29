package net.nacer.customerservice.web;

import net.nacer.customerservice.config.GlobalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
//si jamais la config a changé tu refresh le bean aussi
@RefreshScope
public class ConfigTestController {

 @Autowired
 private GlobalConfig globalConfig;

    @GetMapping("/globalConfig")
    public GlobalConfig globalConfig(){
        return  globalConfig;
    }
}

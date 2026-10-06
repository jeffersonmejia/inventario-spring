package proyecto.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import proyecto.config.AppInfoProperties;
import proyecto.dto.AppInfoResponse;

@RestController
public class InfoController {

    private final AppInfoProperties appInfo;

    public InfoController(AppInfoProperties appInfo) {
        this.appInfo = appInfo;
    }

    @GetMapping({"/api/info", "/api/v1/info"})
    public AppInfoResponse info() {
        return AppInfoResponse.from(appInfo);
    }
}

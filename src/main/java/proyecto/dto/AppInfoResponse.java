package proyecto.dto;

import proyecto.config.AppInfoProperties;

public record AppInfoResponse(
        String name,
        String version,
        String description,
        String environment,
        DeveloperInfo developer) {

    public static AppInfoResponse from(AppInfoProperties properties) {
        AppInfoProperties.Developer developer = properties.getDeveloper();
        return new AppInfoResponse(
                properties.getName(),
                properties.getVersion(),
                properties.getDescription(),
                properties.getEnvironment(),
                new DeveloperInfo(developer.getName(), developer.getEmail(), developer.getRole()));
    }

    public record DeveloperInfo(String name, String email, String role) {
    }
}

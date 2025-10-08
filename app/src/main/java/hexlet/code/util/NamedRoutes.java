package hexlet.code.util;

@SuppressWarnings("java:S1118")
public final class NamedRoutes {
    private static final String URLS = "/urls/";

    private NamedRoutes() {
    }
    public static String rootPath() {
        return "/";
    }

    public static String urlsPath() {
        return "/urls";
    }

    @SuppressWarnings("java:S3400")
    public static String buildUrlPath() {
        return "/urls/build";
    }

    public static String urlPath(Long id) {
        return urlPath(String.valueOf(id));
    }

    public static String urlPath(String id) {
        return URLS + id;
    }

    public static String urlChecksPath(String id) {
        return URLS + id + "/checks";
    }

    public static String urlChecksPath(Long id) {
        return URLS + id + "/checks";
    }
}


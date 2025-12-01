package config;

import display.BranchFirstDisplayStrategy;
import display.DisplayStrategy;
import display.RepositoryFirstDisplayStrategy;
import enums.DisplayMode;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 애플리케이션 설정 관리
 * 설정 파일에서 표시 방식, Git 경로 등을 로드
 */
public class AppConfig {
    private static final String DEFAULT_CONFIG_PATH = "config.properties";
    private static final DisplayMode DEFAULT_DISPLAY_MODE = DisplayMode.REPOSITORY_FIRST;

    private DisplayMode displayMode;
    private String gitPath;

    public AppConfig() {
        this.displayMode = DEFAULT_DISPLAY_MODE;
        this.gitPath = "git"; // 기본값
    }

    /**
     * 설정 파일에서 설정을 로드
     *
     * @param configPath 설정 파일 경로
     * @return AppConfig 인스턴스
     */
    public static AppConfig loadFromFile(String configPath) {
        AppConfig config = new AppConfig();

        try (InputStream input = new FileInputStream(configPath)) {
            Properties prop = new Properties();
            prop.load(input);

            String displayModeStr = prop.getProperty("display.mode", "REPOSITORY_FIRST");
            config.displayMode = DisplayMode.valueOf(displayModeStr.trim().toUpperCase());

            config.gitPath = prop.getProperty("git.path", "git").trim();

        } catch (IOException e) {
            System.out.println("설정 파일을 찾을 수 없습니다. 기본 설정을 사용합니다: " + configPath);
        } catch (IllegalArgumentException e) {
            System.out.println("잘못된 설정값입니다. 기본 설정을 사용합니다.");
        }

        return config;
    }

    /**
     * 기본 설정 파일 경로로 로드
     */
    public static AppConfig loadDefault() {
        return loadFromFile(DEFAULT_CONFIG_PATH);
    }

    /**
     * 설정된 DisplayMode에 따라 적절한 DisplayStrategy 반환
     *
     * @return DisplayStrategy 구현체
     */
    public DisplayStrategy getDisplayStrategy() {
        switch (displayMode) {
            case REPOSITORY_FIRST:
                return new RepositoryFirstDisplayStrategy();
            case BRANCH_FIRST:
                return new BranchFirstDisplayStrategy();
            default:
                throw new IllegalStateException("알 수 없는 DisplayMode: " + displayMode);
        }
    }

    public DisplayMode getDisplayMode() {
        return displayMode;
    }

    public void setDisplayMode(DisplayMode displayMode) {
        this.displayMode = displayMode;
    }

    public String getGitPath() {
        return gitPath;
    }

    public void setGitPath(String gitPath) {
        this.gitPath = gitPath;
    }
}

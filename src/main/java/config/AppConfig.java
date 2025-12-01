package config;

import display.DisplayStrategy;
import enums.DisplayMode;
import service.ChangeDetector;
import service.GitChangeDetector;
import service.GitCommandExecutor;
import service.ProcessBuilderGitExecutor;

/**
 * 애플리케이션 설정 및 의존성 관리 (Singleton)
 * Java 코드에서 직접 설정 및 의존성 생성
 */
public class AppConfig {
    // ========== 여기서 표시 방식을 변경하세요 ==========
    private static final DisplayMode DISPLAY_MODE = DisplayMode.REPOSITORY_FIRST;
    // REPOSITORY_FIRST: 레포지토리 -> 브랜치 -> 변경목록
    // BRANCH_FIRST: 브랜치 -> 레포지토리 -> 변경목록
    // ==================================================

    private static final AppConfig INSTANCE = new AppConfig();

    private final GitCommandExecutor executor;
    private final ChangeDetector detector;
    private final DisplayStrategy displayStrategy;

    private AppConfig() {
        // 의존성 주입 (Dependency Injection)
        this.executor = new ProcessBuilderGitExecutor();
        this.detector = new GitChangeDetector(executor);
        this.displayStrategy = DISPLAY_MODE.getStrategy();
    }

    /**
     * AppConfig 인스턴스 반환 (Singleton)
     */
    public static AppConfig getInstance() {
        return INSTANCE;
    }

    public GitCommandExecutor getExecutor() {
        return executor;
    }

    public ChangeDetector getDetector() {
        return detector;
    }

    public DisplayStrategy getDisplayStrategy() {
        return displayStrategy;
    }

    public DisplayMode getDisplayMode() {
        return DISPLAY_MODE;
    }
}

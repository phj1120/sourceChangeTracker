import display.ChangeDisplayer;
import domain.ChangeInfo;
import domain.Repository;
import enums.RepositoryType;
import service.ChangeTracker;

import java.util.Arrays;
import java.util.List;

/**
 * 변경된 소스 추적 애플리케이션 실행 진입점
 *
 * 설정:
 * - 표시 방식: config/AppConfig.java의 DISPLAY_MODE
 * - 추적 브랜치: 아래 TRACKING_BRANCHES
 * - 레포지토리: enums/RepositoryType.java
 */
public class ChangedSourcePrinterApplication {

    // ========== 여기서 추적할 브랜치를 설정하세요 ==========
    private static final List<String> TRACKING_BRANCHES = Arrays.asList("test1", "test2");
    // ====================================================

    public static void main(String[] args) {
        List<Repository> repositories = RepositoryType.getAllRepositories();

        // 1. 변경사항 추적 (수집)
        List<ChangeInfo> changes = ChangeTracker.track(repositories, TRACKING_BRANCHES);

        // 2. 변경사항 표시
        ChangeDisplayer.display(changes);
    }
}

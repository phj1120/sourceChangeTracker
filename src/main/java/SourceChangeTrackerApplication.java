import display.ChangeDisplayer;
import domain.ChangeInfo;
import domain.Repository;
import enums.RepositoryType;
import service.ChangeTracker;

import java.util.Arrays;
import java.util.List;

public class SourceChangeTrackerApplication {

    /**
     * 추적할 브랜치 목록 설정
     * 기준 브랜치와 비교하여 변경사항을 확인할 브랜치들을 지정
     */
    private static final List<String> TRACKING_BRANCHES = Arrays.asList(
            "origin/test1",
            "origin/test2"
    );

    public static void main(String[] args) {
        List<Repository> repositories = RepositoryType.getAllRepositories();

        List<ChangeInfo> changes = ChangeTracker.track(repositories, TRACKING_BRANCHES);

        ChangeDisplayer.display(changes);
    }
}

package service;

import config.AppConfig;
import domain.ChangeInfo;
import domain.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 변경사항 추적 비즈니스 로직
 * Single Responsibility: 변경사항 수집만 담당
 */
public class ChangeTracker {
    private final ChangeDetector detector;

    private ChangeTracker(ChangeDetector detector) {
        this.detector = detector;
    }

    /**
     * 설정을 기반으로 변경사항을 추적
     *
     * @param repositories 추적할 레포지토리 목록
     * @param targetBranches 추적할 브랜치 목록
     * @return 수집된 변경사항 목록
     */
    public static List<ChangeInfo> track(List<Repository> repositories, List<String> targetBranches) {
        AppConfig config = AppConfig.getInstance();
        ChangeTracker tracker = new ChangeTracker(config.getDetector());
        return tracker.collectAllChanges(repositories, targetBranches);
    }

    private List<ChangeInfo> collectAllChanges(List<Repository> repositories, List<String> targetBranches) {
        List<ChangeInfo> allChanges = new ArrayList<>();

        for (Repository repo : repositories) {
            for (String branch : targetBranches) {
                try {
                    List<String> changes = detector.detectChanges(repo, branch);

                    if (!changes.isEmpty()) {
                        allChanges.add(new ChangeInfo(repo, branch, changes));
                    }

                } catch (Exception e) {
                    System.err.println("경고: " + repo.getName() + " (" + branch + ") 처리 실패 - " + e.getMessage());
                }
            }
        }

        return allChanges;
    }

}

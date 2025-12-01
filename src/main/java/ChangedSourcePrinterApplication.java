import config.AppConfig;
import display.DisplayStrategy;
import domain.ChangeInfo;
import domain.Repository;
import enums.RepositoryType;
import service.ChangeDetector;
import service.GitChangeDetector;
import service.GitCommandExecutor;
import service.ProcessBuilderGitExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 변경된 소스 추적 애플리케이션
 *
 * 사용법:
 * java ChangedSourcePrinterApplication branch1 branch2 branch3 ...
 *
 * 설정 파일 (config.properties):
 * display.mode=REPOSITORY_FIRST 또는 BRANCH_FIRST
 * git.path=git
 */
public class ChangedSourcePrinterApplication {

    public static void main(String[] args) {
        if (args.length == 0) {
            printUsage();
            return;
        }

        try {
            // 설정 로드
            AppConfig config = AppConfig.loadDefault();
            System.out.println("표시 방식: " + config.getDisplayMode());

            // 의존성 주입 (Dependency Injection)
            GitCommandExecutor executor = new ProcessBuilderGitExecutor();
            ChangeDetector detector = new GitChangeDetector(executor);
            DisplayStrategy displayStrategy = config.getDisplayStrategy();

            // 애플리케이션 실행
            ChangedSourcePrinterApplication app = new ChangedSourcePrinterApplication(
                    detector,
                    displayStrategy
            );

            // 브랜치 목록
            List<String> branches = Arrays.asList(args);
            System.out.println("대상 브랜치: " + branches + "\n");

            // 변경사항 추적 및 출력
            app.run(branches);

        } catch (Exception e) {
            System.err.println("오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private final ChangeDetector detector;
    private final DisplayStrategy displayStrategy;

    public ChangedSourcePrinterApplication(ChangeDetector detector, DisplayStrategy displayStrategy) {
        this.detector = detector;
        this.displayStrategy = displayStrategy;
    }

    /**
     * 모든 레포지토리에 대해 지정된 브랜치들의 변경사항을 추적하고 출력
     *
     * @param targetBranches 추적할 브랜치 목록
     */
    public void run(List<String> targetBranches) {
        List<Repository> repositories = RepositoryType.getAllRepositories();
        List<ChangeInfo> allChanges = collectAllChanges(repositories, targetBranches);

        displayStrategy.display(allChanges);

        printSummary(allChanges);
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

    private void printSummary(List<ChangeInfo> allChanges) {
        int totalRepositories = (int) allChanges.stream()
                .map(info -> info.getRepository().getName())
                .distinct()
                .count();

        int totalBranches = (int) allChanges.stream()
                .map(ChangeInfo::getBranch)
                .distinct()
                .count();

        int totalFiles = allChanges.stream()
                .mapToInt(info -> info.getChangedFiles().size())
                .sum();

        System.out.println("========== 요약 ==========");
        System.out.println("레포지토리 수: " + totalRepositories);
        System.out.println("브랜치 수: " + totalBranches);
        System.out.println("변경된 파일 수: " + totalFiles);
        System.out.println("==========================");
    }

    private static void printUsage() {
        System.out.println("사용법: java ChangedSourcePrinterApplication <branch1> <branch2> ...");
        System.out.println();
        System.out.println("예시:");
        System.out.println("  java ChangedSourcePrinterApplication feature/user-auth");
        System.out.println("  java ChangedSourcePrinterApplication develop release/v1.0");
        System.out.println();
        System.out.println("설정 파일 (config.properties):");
        System.out.println("  display.mode=REPOSITORY_FIRST  # 또는 BRANCH_FIRST");
        System.out.println("  git.path=git");
    }
}

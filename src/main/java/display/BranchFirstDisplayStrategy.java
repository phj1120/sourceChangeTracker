package display;

import domain.ChangeInfo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 브랜치 우선 출력 전략
 * 출력 형식: 브랜치 -> 레포지토리 -> 변경목록
 */
public class BranchFirstDisplayStrategy implements DisplayStrategy {

    @Override
    public void display(List<ChangeInfo> changes) {
        if (changes.isEmpty()) {
            System.out.println("변경사항이 없습니다.");
            return;
        }

        Map<String, Map<String, List<String>>> grouped = groupByBranch(changes);

        System.out.println("\n========== 변경사항 (브랜치 우선) ==========\n");

        grouped.forEach((branch, repositories) -> {
            System.out.println("Branch: " + branch);

            repositories.forEach((repoName, files) -> {
                System.out.println("  Repository: " + repoName + " (" + files.size() + " files)");

                files.forEach(file -> System.out.println("    - " + file));

                System.out.println();
            });
        });

        System.out.println("==========================================\n");
    }

    private Map<String, Map<String, List<String>>> groupByBranch(List<ChangeInfo> changes) {
        return changes.stream()
                .collect(Collectors.groupingBy(
                        ChangeInfo::getBranch,
                        LinkedHashMap::new,
                        Collectors.groupingBy(
                                info -> info.getRepository().getName(),
                                LinkedHashMap::new,
                                Collectors.flatMapping(
                                        info -> info.getChangedFiles().stream(),
                                        Collectors.toList()
                                )
                        )
                ));
    }
}

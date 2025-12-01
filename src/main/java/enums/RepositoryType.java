package enums;

import domain.Repository;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 추적할 레포지토리 설정
 * 각 레포지토리의 이름, 기준 브랜치, 경로를 지정
 * 형식: ENUM_NAME("레포지토리명", "기준브랜치", "절대경로")
 */
public enum RepositoryType {
    CHANGED_SOURCE_TRACKING("changedSourceTracking", "origin/main", "/Users/parkh/Dev/git/Project/changedSourceTracking"),
    VIBE_PAY("vibePay", "origin/main", "/Users/parkh/Dev/git/Project/vibe-pay"),
//     B2CKSHOP_EMC("b2ckshop-emc", "master", "C:/path/to/b2ckshop-emc"),
//     NKSHOP_EMC("nkshop-emc", "master", "C:/path/to/nkshop-emc"),
//     NKSHOP_BOS("nkshop-bos", "master", "C:/path/to/nkshop-bos"),
    ;

    private final String name;
    private final String baseBranch;
    private final String path;

    RepositoryType(String name, String baseBranch, String path) {
        this.name = name;
        this.baseBranch = baseBranch;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getBaseBranch() {
        return baseBranch;
    }

    public String getPath() {
        return path;
    }

    public Repository toRepository() {
        return new Repository(name, baseBranch, Paths.get(path));
    }

    public static List<Repository> getAllRepositories() {
        return Arrays.stream(values())
                .map(RepositoryType::toRepository)
                .collect(Collectors.toList());
    }
}

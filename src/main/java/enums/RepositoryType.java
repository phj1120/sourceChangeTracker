package enums;

import domain.Repository;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 레포지토리 타입 정의
 * 레포지토리명, 기준 브랜치, 폴더 위치를 관리
 */
public enum RepositoryType {
    // 테스트용: 현재 프로젝트만 활성화
    CHANGED_SOURCE_TRACKING("changedSourceTracking", "main", "/Users/parkh/Dev/git/Project/changedSourceTracking");

    // 실제 사용 시 아래 경로들을 추가/수정하세요
    // Windows 경로 예시: "C:/path/to/repo"
    // Mac/Linux 경로 예시: "/Users/username/path/to/repo"
    // B2CKSHOP_EMC("b2ckshop-emc", "master", "C:/path/to/b2ckshop-emc"),
    // NKSHOP_EMC("nkshop-emc", "master", "C:/path/to/nkshop-emc"),
    // NKSHOP_BOS("nkshop-bos", "master", "C:/path/to/nkshop-bos");

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

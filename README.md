# Source Change Tracker

Git 레포지토리의 브랜치별 소스 변경사항을 추적하는 Java 애플리케이션

## 설정

### 1. 레포지토리 경로 설정
`src/main/java/enums/RepositoryType.java`에서 추적할 레포지토리 정보 입력:

```java
public enum RepositoryType {
    REPO1("repo-name", "master", "/path/to/repo1"),
    REPO2("repo-name", "master", "/path/to/repo2"),
    ;
}
```

### 2. 추적 브랜치 설정
`src/main/java/SourceChangeTrackerApplication.java`에서 추적할 브랜치 입력:

```java
private static final List<String> TRACKING_BRANCHES = Arrays.asList("develop", "release/v1.0");
```

### 3. 출력 방식 설정 (선택)
`src/main/java/config/AppConfig.java`에서 출력 방식 선택:

```java
private static final DisplayMode DISPLAY_MODE = DisplayMode.REPOSITORY_FIRST;
// REPOSITORY_FIRST: 레포지토리 -> 브랜치 -> 변경목록
// BRANCH_FIRST: 브랜치 -> 레포지토리 -> 변경목록
```
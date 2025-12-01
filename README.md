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

### 제약사항

프로그램 실행전에 대상 되는 레포지토리를 fetch 받은 후 실행 할 것.

- 로컬에 있는 깃을 이용해 하기 때문에, fetch 받지 않으면 과거의 브랜치 기준으로 비교 될 수 있음.
- origin 을 붙이면 로컬에 체크아웃하지 않아도 조회해 올 수 있음.  

기준 브랜치(master)가 업데이트 된다면, 대상 브랜치(feature)에 기준 브랜치를 머지 해줄 것.

- 대상 브랜치로 수정된 파일을 보여주는 것이 아니라, 단순히 기준 브랜치와 대상 브랜치의 변경 사항을 비교하는 것임.(git diff)
- 대상 브랜치를 딴 시점 이후에 기준 브랜치가 업데이트 된다면, 대상 브랜치에서 수정하지 않은 내용이 나올 수 있음.
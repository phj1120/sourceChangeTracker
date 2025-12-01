package enums;

/**
 * 출력 방식 정의
 * REPOSITORY_FIRST: 레포지토리 -> 브랜치 -> 변경목록
 * BRANCH_FIRST: 브랜치 -> 레포지토리 -> 변경목록
 */
public enum DisplayMode {
    REPOSITORY_FIRST,
    BRANCH_FIRST
}

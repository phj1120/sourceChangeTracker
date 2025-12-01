package enums;

import display.BranchFirstDisplayStrategy;
import display.DisplayStrategy;
import display.RepositoryFirstDisplayStrategy;

/**
 * 출력 방식 정의
 * REPOSITORY_FIRST: 레포지토리 -> 브랜치 -> 변경목록
 * BRANCH_FIRST: 브랜치 -> 레포지토리 -> 변경목록
 */
public enum DisplayMode {
    REPOSITORY_FIRST(new RepositoryFirstDisplayStrategy()),
    BRANCH_FIRST(new BranchFirstDisplayStrategy());

    private final DisplayStrategy strategy;

    DisplayMode(DisplayStrategy strategy) {
        this.strategy = strategy;
    }

    public DisplayStrategy getStrategy() {
        return strategy;
    }
}

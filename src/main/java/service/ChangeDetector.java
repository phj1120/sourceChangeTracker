package service;

import domain.Repository;

import java.util.List;

/**
 * 변경사항 감지 인터페이스
 * Interface Segregation Principle 적용 - 단일 책임
 */
public interface ChangeDetector {
    /**
     * 지정된 브랜치의 변경된 파일 목록 반환
     *
     * @param repository 대상 레포지토리
     * @param targetBranch 비교할 브랜치
     * @return 변경된 파일 경로 목록
     */
    List<String> detectChanges(Repository repository, String targetBranch);
}

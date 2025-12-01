package display;

import domain.ChangeInfo;

import java.util.List;

/**
 * 출력 전략 인터페이스
 * Strategy Pattern 적용 - 출력 방식을 런타임에 변경 가능
 */
public interface DisplayStrategy {
    /**
     * 변경 정보를 출력
     *
     * @param changes 변경 정보 목록
     */
    void display(List<ChangeInfo> changes);
}

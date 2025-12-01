package display;

import config.AppConfig;
import domain.ChangeInfo;

import java.util.List;

/**
 * 변경사항 표시 담당
 * Single Responsibility: 변경사항 표시만 담당
 */
public class ChangeDisplayer {

    /**
     * 설정을 기반으로 변경사항을 표시
     *
     * @param changes 표시할 변경사항 목록
     */
    public static void display(List<ChangeInfo> changes) {
        AppConfig config = AppConfig.getInstance();
        DisplayStrategy strategy = config.getDisplayStrategy();
        strategy.display(changes);
    }
}

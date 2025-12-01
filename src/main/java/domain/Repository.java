package domain;

import java.nio.file.Path;
import java.util.Objects;

/**
 * 레포지토리 도메인 모델
 * 불변 객체로 설계 (final 필드, setter 없음)
 */
public class Repository {
    private final String name;
    private final String baseBranch;
    private final Path path;

    public Repository(String name, String baseBranch, Path path) {
        this.name = Objects.requireNonNull(name, "Repository name은 필수입니다.");
        this.baseBranch = Objects.requireNonNull(baseBranch, "Base branch는 필수입니다.");
        this.path = Objects.requireNonNull(path, "Path는 필수입니다.");
    }

    public String getName() {
        return name;
    }

    public String getBaseBranch() {
        return baseBranch;
    }

    public Path getPath() {
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repository that = (Repository) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Repository{" +
                "name='" + name + '\'' +
                ", baseBranch='" + baseBranch + '\'' +
                ", path=" + path +
                '}';
    }
}

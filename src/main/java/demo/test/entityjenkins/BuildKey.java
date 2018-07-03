package demo.test.entityjenkins;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BuildKey implements Serializable {

    @NotNull
    @Size(max = 30)
    @Column(name = "jobName", nullable = false)
    @Getter @Setter private String jobName;

    @NotNull
    @Column(name = "buildId", nullable = false)
    @Getter @Setter private int buildId;

    public BuildKey(){
    }

    public BuildKey(String jobName, int buildId){
        this.jobName = jobName;
        this.buildId = buildId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BuildKey)) return false;
        BuildKey that = (BuildKey) o;
        return Objects.equals(getJobName(), that.getJobName()) &&
                Objects.equals(getBuildId(), that.getBuildId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getJobName(), getBuildId());
    }

}

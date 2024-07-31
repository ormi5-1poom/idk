package team.onepoom.idk.domain.user.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.onepoom.idk.domain.user.User;

@Getter
@NoArgsConstructor
public class WriterDTO {

    private long id;
    private String email;
    private String nickName;

    public WriterDTO(User writer) {
        this.id = writer.getId();
        this.email = writer.getEmail();
        this.nickName = writer.getNickname();
    }

    @QueryProjection
    public WriterDTO(long id, String email, String nickName) {
        this.id = id;
        this.email = email;
        this.nickName = nickName;
    }
}

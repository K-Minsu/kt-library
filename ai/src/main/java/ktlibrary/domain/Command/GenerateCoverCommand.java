package ktlibrary.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class GenerateCoverCommand {

    private Long id;                    // 도서 ID
    private String manuscriptTitle;     // 원고 제목
    private String manuscriptContent;   // 원고 내용
    private Long authorId;              // 작가 ID
    private String authorName;          // 작가 이름
    private String introduction;        // 작가 소개
}

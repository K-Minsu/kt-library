package ktlibrary.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class RegisterManuscriptCommand {

    private AuthorId authorId;
    private String manuscriptTitle;
    private String manuscriptContent;
    private Long id;
}

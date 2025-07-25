package ktlibrary.api;

import ktlibrary.infra.application.service.SummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;


/**
 * SummaryTestController
 *
 * 테스트용 컨트롤러로, 책 내용을 요약하는 SummaryService의 기능을 확인하기 위한 엔드포인트를 제공합니다.
 * resources 폴더 내에 위치한 test_book.txt 파일의 내용을 읽고,
 * 생성형 AI(OpenAI GPT)를 통해 요약한 결과를 반환합니다.
 *
 * 이 컨트롤러는 개발 및 테스트 목적으로만 사용됨.
 */
@RestController
@RequiredArgsConstructor
public class SummaryTestController {

    private final SummaryService summaryService;

    /**
     * test-summary 엔드포인트는 test_book2.txt 파일의 내용을 읽어 요약 결과를 반환합니다.
     * @return 요약된 책 내용 문자열
     */
    @GetMapping("/test-summary")
    public String testSummary() {
        try {
            // 1. resources 폴더의 루트에 위치한 test_book.txt 파일을 읽습니다.
            // ClassPathResource를 사용하면 빌드된 JAR 내부에서도 리소스를 접근할 수 있습니다.
            ClassPathResource resource = new ClassPathResource("test_book2.txt");

            String content;
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
                
                // 2. 파일의 모든 줄을 하나의 문자열로 결합합니다. (줄바꿈 기준)
                // 만약 책 내용이 매우 길다면 이 문자열은 SummaryService 내부에서 chunking 처리됩니다.
                content = reader.lines().collect(Collectors.joining("\n"));
            }

            /**
             * 테스트를 위한 하드 코딩
             * 이후에 삭제
             */
            String manuscriptTitle = "소나기";
            String authorName = "황순원";
            String introduction = "대한민국의 시인이자 소설가. 김동리, 김승옥과 함께 한국 현대문학을 대표하는 소설가로 평가받는다.";

            // 3. 요약 서비스에 책 내용을 전달하고, 결과 문자열을 반환합니다.
            return summaryService.summarize(manuscriptTitle, authorName, introduction, content);

        } catch (Exception e) {
            // 파일을 읽거나 요약 중 문제가 발생한 경우, 에러 메시지를 반환합니다.
            e.printStackTrace();
            return "파일을 읽거나 요약하는 도중 오류가 발생했습니다: " + e.getMessage();
        }
    }
}
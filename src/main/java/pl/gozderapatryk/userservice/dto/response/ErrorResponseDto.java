package pl.gozderapatryk.userservice.dto.response;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Map;

@Value
@Builder(toBuilder = true)
public class ErrorResponseDto {
    String message;
    String path;
    Map<String, ?> details;
    LocalDateTime createdAt;
}

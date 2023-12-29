package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum HttpMethod {
    Post("Post"), Get("Get");
    private final String value;
}

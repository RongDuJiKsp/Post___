package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

@AllArgsConstructor
@Getter
@Setter
public class BodyContain {
    private boolean isUsingJSON;
    private boolean isUsingBin;
    private File selectedFile;
    private String stringEntity;
}

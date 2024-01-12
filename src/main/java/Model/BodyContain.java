package Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;

@AllArgsConstructor
@Data
public class BodyContain {
    private boolean isUsingJSON;
    private boolean isUsingBin;
    private File selectedFile;
    private String stringEntity;
}

import java.util.List;

/**
 * Created by user on 7/26/15.
 */
public interface SyntaxValidator {
    boolean validateQuery();
    List<Integer> markedErrors();
}
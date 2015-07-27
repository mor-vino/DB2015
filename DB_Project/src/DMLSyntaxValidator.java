import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 7/26/15.kk
 */
public class DMLSyntaxValidator implements SyntaxValidator {
    private String query;
    private List<String> specialWords;
    List<Integer> markedErrors;

    public DMLSyntaxValidator() {
        this.initializeSpecialWords();
        this.markedErrors = new ArrayList<Integer>();
    }

    public DMLSyntaxValidator(String query) {
        this.query = query;
        this.initializeSpecialWords();
        this.markedErrors = new ArrayList<Integer>();
    }

    private void initializeSpecialWords() {
        this.specialWords = new ArrayList<String>();
        this.specialWords.add("SELECT");
        this.specialWords.add("FROM");
        this.specialWords.add("WHERE");
        this.specialWords.add("LIKE");
        this.specialWords.add("HAVING");
        this.specialWords.add("AND");
        this.specialWords.add("OR");
        this.specialWords.add("ORDER BY");
        this.specialWords.add("ASC");
        this.specialWords.add("DESC");
        this.specialWords.add("GROUP BY");
        this.specialWords.add("LEFT JOIN");
        this.specialWords.add("RIGHT JOIN");
        this.specialWords.add("OUTER JOIN");
        this.specialWords.add("AS");
        this.specialWords.add("AVG(");
        this.specialWords.add("MIN(");
        this.specialWords.add("MAX(");
        this.specialWords.add("SUM(");
        this.specialWords.add("COUNT(");
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public boolean validateQuery() {
        return this.validateSelectFromStructure() && this.validateSpecialWords();
    }

    @Override
    public List<Integer> markedErrors() {
        return this.markedErrors;
    }

    private boolean validateSelectFromStructure() {
        String[] splitQuery = this.query.split(" ");
        if (!splitQuery[0].toUpperCase().equals("SELECT") || !splitQuery[2].toUpperCase().equals("FROM")) {
            return false;
        }
        return true;
    }

    private boolean validateSpecialWords() {
        boolean flag = true;
        String[] splitQuery = this.query.split(" ");

        for (int i = 0; i < splitQuery.length; i += 2) {
            if (!this.specialWords.contains(splitQuery[i].toUpperCase())
                    || !splitQuery[i].equals(splitQuery[i].toUpperCase())) {
                this.markedErrors.add(i);
                flag = false;
            }
        }
        return flag;
    }
}

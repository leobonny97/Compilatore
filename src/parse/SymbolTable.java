package parse;

import exception.MultipleDeclarationException;
import java.util.HashMap;
import nodes.Identifier;

public class SymbolTable extends HashMap<String, Identifier> {
    private String name;

    public SymbolTable(String n) {
        this.name = n;
    }

    public Identifier installID(Identifier id) throws MultipleDeclarationException {
        System.out.println("Symbol table say: ID Ricevuto: " + id.getId());
        if (this.containsKey(id.getId())) {
            throw new MultipleDeclarationException(id.getId());
        } else {
            this.put(id.getId(), id);
            return id;
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package nodes;

import org.w3c.dom.Element;

public interface Visitable {
    public Element accept(Visitor visitor);
}

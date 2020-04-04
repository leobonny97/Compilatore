package nodes;

import org.w3c.dom.Element;

public interface Stat {
    public Element accept(Visitor visitor);
    public String getNodeType();
    public void setNodeType(String nodeType);
}

package lib;

import java.util.LinkedList;
import java.util.List;

public class Child {
    private List<String> childNames;
    private List<String> childIdNumbers;

    public Child() {
        childNames = new LinkedList<>();
        childIdNumbers = new LinkedList<>();
    }

    public void addChild(String childName, String childIdNumber) {
        childNames.add(childName);
        childIdNumbers.add(childIdNumber);
    }

    // Getters
    public List<String> getChildNames() {
        return childNames;
    }

    public List<String> getChildIdNumbers() {
        return childIdNumbers;
    }
}

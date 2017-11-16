package com.example.user.tinkoffhomework7;

import java.util.List;

/**
 * Created by User on 11.11.2017.
 */

public class Node {

    private long id;
    private int value;
    private List<Node> nodes;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void addChildNode(Node node){
        nodes.add(node);
    }
}

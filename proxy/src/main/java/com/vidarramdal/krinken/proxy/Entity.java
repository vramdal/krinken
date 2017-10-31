package com.vidarramdal.krinken.proxy;

import java.util.HashSet;
import java.util.Set;

public abstract class Entity<T> {

    private Entity parent;
    private String relativeCssSelector;
    private Set<Entity> children = new HashSet<>();

    public Entity(Entity parent, String relativeCssSelector) {
        this.parent = parent;
        this.relativeCssSelector = relativeCssSelector;
    }

    public Entity getParent() {
        return parent;
    }

    public String getRelativeCssSelector() {
        return relativeCssSelector;
    }

    public Set<Entity> getChildren() {
        return children;
    }
}




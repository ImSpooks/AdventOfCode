package me.imspooks.adventofcode.util;

public record Pair<F, S>(F first, S second) {

    public F key() {
        return this.first;
    }

    public S value() {
        return this.second;
    }
}
package model;

public enum ItemType {

    BOOK("Buch"), EBOOK("eBook"), ALBUM("Album"), MOVIE("Film"), SHOW("Serie");

    private final String name;

    ItemType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

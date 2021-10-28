package ru.event.dto;

public enum PrintType {
  MAP("map"),
  LIST("list"),
  ALONE("alone");

  private String description;

  PrintType(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}

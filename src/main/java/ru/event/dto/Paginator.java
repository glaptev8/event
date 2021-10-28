package ru.event.dto;

public class Paginator {
  public static final int DEFAULT_PAGE_SIZE = 20;
  public static final int MAX_PAGE_SIZE = 1000;

  private final int pageSize;
  private final int pageNumber;
  private int pagesCount;

  public Paginator(int pageNumber, int pageSize) {
    this(pageNumber, pageSize, DEFAULT_PAGE_SIZE, MAX_PAGE_SIZE);
  }

  public Paginator(int pageNumber, int pageSize, int defaultPageSize, int maxPageSize) {
    if (defaultPageSize <= 0) throw new IllegalArgumentException("defaultPageSize can't be less or equal zero");
    if (maxPageSize < defaultPageSize) throw new IllegalArgumentException("maxPageSize can't be less that defaultPageSize");

    this.pageNumber = Math.max(pageNumber, 1);
    if (pageSize <= 0) {
      this.pageSize = defaultPageSize;
    } else if (pageSize > maxPageSize) {
      this.pageSize = maxPageSize;
    } else {
      this.pageSize = pageSize;
    }
  }

  public int getPageSize() {
    return pageSize;
  }

  public int getPageNumber() {
    return pageNumber;
  }

  public int getPagesCount() {
    return pagesCount;
  }

  public void setPagesCount(int size) {
    if (size % pageSize == 0) {
      this.pagesCount = size / pageSize;
    } else {
      this.pagesCount = (size / pageSize) + 1;
    }
  }

  public int offset() {
    return (pageNumber - 1) * pageSize;
  }
}


// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Pager.java

package cn.easybuy.utils;

import lombok.Data;

@Data
public class Pager {
    private int currentPage;
    private int rowCount;
    private int rowPerPage;
    private int pageCount;
    private String url;

    public Pager(int rowCount, int rowPerPage, int currentPage) {
        this.rowCount = rowCount;
        this.rowPerPage = rowPerPage;
        this.currentPage = currentPage;
        if (this.rowCount % this.rowPerPage == 0)
            pageCount = this.rowCount / this.rowPerPage;
        else if (this.rowCount % this.rowPerPage > 0)
            pageCount = this.rowCount / this.rowPerPage + 1;
        else
            pageCount = 0;
    }
}

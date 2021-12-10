package com.ithotel.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;



public class TagCurrentDate extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        String currentDate = date.toString();

        pageContext.setAttribute("currentDate", currentDate);

        return SKIP_BODY;
    }
}

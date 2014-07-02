
package com.acme.messages;

import java.util.ArrayList;
import java.util.List;
import com.daalitoy.demo.codemodel.base.Message;

public class MailMessage
    extends Message
{

    private static List<java.lang.String> fieldNamesList = new ArrayList<java.lang.String>();

    static {
        fieldNamesList.add("Recipient");
        fieldNamesList.add("Content");
    }

    public MailMessage(java.lang.String recipient, java.lang.String content) {
        setObject(recipient, content);
    }

    /**
     * setter method for Recipient
     * 
     */
    public MailMessage setRecipient(final java.lang.String objName, final Object objValue) {
        setObject(objName, objValue);
        return this;
    }

    public java.lang.String getRecipient(final java.lang.String objName) {
        return ((String) getObject(objName));
    }

    public boolean hasRecipient(final java.lang.String objName) {
        return hasObject(objName);
    }

    /**
     * setter method for Content
     * 
     */
    public MailMessage setContent(final java.lang.String objName, final Object objValue) {
        setObject(objName, objValue);
        return this;
    }

    public java.lang.String getContent(final java.lang.String objName) {
        return ((String) getObject(objName));
    }

    public boolean hasContent(final java.lang.String objName) {
        return hasObject(objName);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; (i< 2); i += 1) {
            builder.append((String.format("%s : %s\n",fieldNamesList.get(i),getObject(fieldNamesList.get(i)))));
        }
        return builder.toString();
    }

}

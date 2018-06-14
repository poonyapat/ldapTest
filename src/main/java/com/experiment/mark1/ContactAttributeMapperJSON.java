package com.experiment.mark1;


import org.json.JSONException;
import org.springframework.ldap.core.AttributesMapper;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import org.json.JSONObject;


public class ContactAttributeMapperJSON implements AttributesMapper {
    @Override
    public Object mapFromAttributes(Attributes attributes) throws NamingException {
        NamingEnumeration<String> ids = attributes.getIDs();
        JSONObject jo = new JSONObject();
        while (ids.hasMore()) {
            String id = ids.next();
            try {
                jo.put(id, attributes.get(id).get());
            } catch (JSONException je) {
                je.printStackTrace();
            }
        }
        return jo.toString();
    }
}

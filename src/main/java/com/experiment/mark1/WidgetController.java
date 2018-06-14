package com.experiment.mark1;

import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/")
@RestController
public class WidgetController {

    @RequestMapping(method = RequestMethod.GET)
    public List<String> index() {
        String url = "ldap://ldap.forumsys.com:389";
        String base = "dc=example,dc=com";
//        String userDn = "cn=read-only-admin,dc=example,dc=com";
//        String password = "password";
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl(url);
        contextSource.setBase(base);
//        contextSource.setUserDn(userDn);
//        contextSource.setPassword(password);
        contextSource.afterPropertiesSet();
        LdapTemplate ldapTemplate = new LdapTemplate(contextSource);

        // get users
        AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter("objectclass", "Person"));
        @SuppressWarnings("unchecked")
        List<String> list = ldapTemplate.search("", filter.encode(),
                new ContactAttributeMapperJSON());
        return list;
    }
}

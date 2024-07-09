package dev.ducku.securitye78.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class DemoController {

    private static final Log log = LogFactory.getLog(DemoController.class);
    private Logger logger = Logger.getLogger(DemoController.class.getName());

    @GetMapping("/demo1")
    @PreAuthorize("hasAuthority('read')")
    public String demo() {
        logger.info("demo1");
        return "Demo 1";
    }

    @GetMapping("/demo2")
    @PreAuthorize("hasAnyAuthority('write', 'read')")
    public String demo2() {
        return "Demo 2";
    }


    @GetMapping("/demo3/{smth}")
    @PreAuthorize("""
                    #something == authentication.name or
                    authentication.name == 'bill'    
            """)
    public String demo3(@PathVariable("smth") String something) {
        return "Demo 3";
    }

    @GetMapping("/demo4/{smth}")
    @PreAuthorize("@complexConditionEvaluator.complexCondition()")
    public String demo4(@PathVariable("smth") String something) {
        return "Demo 4 " + something;
    }

    @GetMapping("/demo5")
    //is mainly used when we want to restrict the access to some returned value 😎
    @PostAuthorize("returnObject != 'hehe'")
    public String demo5(Principal principal) {
        logger.info("😉");
        if (principal.getName().equals("bill")) {
            return "Demo 5 " + "😉";
        }
        return "hehe";
    }


    @GetMapping("/demo6")
    @PreFilter("filterObject.contains('a')")
    public String demo6(@RequestBody List<String> values) {
        logger.info("Values: " + values + "🧐");
        return "demo6";
    }


    // @PostFilter  => the returned type must be either a Collection or an array
    @GetMapping("/demo7")
    @PostFilter("filterObject.contains('a')")
    public List<String> demo7() {
        List<String> list = new ArrayList<>();
        list.add("abcd");
        list.add("wert");
        list.add("qaaz");
        list.add("wrty");
        logger.info("List: " + list + "❤️");
        // List.of(...) // List.of creates and immutable collection
        return list;
    }
}
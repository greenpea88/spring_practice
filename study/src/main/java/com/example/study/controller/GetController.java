package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") //class에 대해서는 주소가 겹쳐도 상관 없음
public class GetController {

    @RequestMapping(method = RequestMethod.GET,path="/getMethod")
    public String getRequest()
    {
        return "Hi getMethod";
    }

    @GetMapping("/getParameter") //method에 대해서만 주소가 겹치면 안됨
    public String getParameter(@RequestParam String id, @RequestParam(name="password") String pwd)
    {
        //local변수와 받는 parameter의 이름이 겹치면 안되기 때문에 (name = http에서 받는 이름) 으로 param의 이름과 매칭을 시키도록 할 수 있음
        String password="bbbb";
        System.out.println("id : "+id);
        System.out.println("password : "+pwd);

        return id+pwd;
    }

    @GetMapping("/getMultiParameter")
    public SearchParam getMultiParameter(SearchParam searchParam)
    {
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        return searchParam;
    }
}

package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    //웹 어플리케이션에서 /hello로 들어오면 이 메서드(String hello)를 호출해준다.
    @GetMapping("hello")
    public String hello(Model model) { //moel : mvc패턴의 그 모델
        //키로 넣었던 data의 value가 hello!!
        model.addAttribute("data", "hello!!"); //data를 hello로 넘김

        return "hello";
    }

    @GetMapping("hello-mvc")
    //외부에서 파라미터를 받는다
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //http의 응답 body부분에 반환값을 넣어주겠다는 의미
    public String helloString(@RequestParam("name") String name, Model model){
        return "hello "+name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();      //객체 생성
        hello.setName(name);
        return hello;       //hello 라는 객체 넘겨주기
    }
    static class Hello{         //클래스 안에서 또 이 클래스를 쓸 수 있다
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}

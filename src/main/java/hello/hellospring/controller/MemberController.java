package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {

        this.memberService = memberService;
      //  System.out.println("membeService =" + memberService.getClass());;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return"redirect:/";
    }

    @GetMapping("/members") // 회원 리스트를 조회한다.
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members); // HTML에 전해줄 데이터 model에 담기.
        return "members/memberList";
    }
}

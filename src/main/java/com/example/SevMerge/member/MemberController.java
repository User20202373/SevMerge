package com.example.SevMerge.member;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //  회원가입
    @GetMapping("/join-form")
    public String joinForm() {
        return "member/join-form";
    }

    @PostMapping("/join")
    public String join(MemberRequest.Join request) {
        memberService.join(request);
        return "redirect:/login-form";
    }

    // 로그인 / 로그아웃
    @GetMapping("/login-form")
    public String loginForm() {
        return "member/login-form";
    }

    @PostMapping("/login")
    public String login(MemberRequest.Login request, HttpSession session) {
        memberService.login(request, session);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        memberService.logout(session);
        return "redirect:/";
    }

    // 마이페이지
    @GetMapping("/mypage")
    public String mypage(HttpSession session, Model model) {
        Member loginMember = (Member) session.getAttribute("sessionUser");
        model.addAttribute("member", memberService.getMyInfo(loginMember.getId()));
        return "member/mypage";
    }

    @PostMapping("/mypage/update")
    public String updateMyInfo(MemberRequest.Update request, HttpSession session) {
        Member loginMember = (Member) session.getAttribute("sessionUser");
        memberService.updateMyInfo(loginMember.getId(), request);
        return "redirect:/mypage";
    }

    @PostMapping("/mypage/password")
    public String changePassword(MemberRequest.ChangePassword request, HttpSession session) {
        Member loginMember = (Member) session.getAttribute("sessionUser");
        memberService.changePassword(loginMember.getId(), request);
        return "redirect:/mypage";
    }

    // 관리자 전용
    @GetMapping("/admin/members")
    public String memberList(Model model) {
        model.addAttribute("members", memberService.getPendingExperts());
        return "admin/member-list";
    }

    @PostMapping("/admin/members/{memberId}/approve")
    public String approveExpert(@PathVariable Long memberId) {
        memberService.approveExpert(memberId);
        return "redirect:/admin/members";
    }

    @PostMapping("/admin/members/{memberId}/reject")
    public String rejectExpert(@PathVariable Long memberId) {
        memberService.rejectExpert(memberId);
        return "redirect:/admin/members";
    }

    @PostMapping("/admin/members/{memberId}/suspend")
    public String suspendMember(@PathVariable Long memberId) {
        memberService.suspendMember(memberId);
        return "redirect:/admin/members";
    }
}
package br.com.lucasmoraes.FrontGestaoVagas.modules.candidate.controller;

import br.com.lucasmoraes.FrontGestaoVagas.modules.candidate.service.ApplyJobService;
import br.com.lucasmoraes.FrontGestaoVagas.modules.candidate.service.CandidateService;
import br.com.lucasmoraes.FrontGestaoVagas.modules.candidate.service.FindJobService;
import br.com.lucasmoraes.FrontGestaoVagas.modules.candidate.service.ProfileCandidateService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;


@Controller
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    private CandidateService candidateService;

    @Autowired
    private ProfileCandidateService profileCandidateService;

    @Autowired
    private FindJobService findJobService;

    @Autowired
    private ApplyJobService applyJobService;

    @GetMapping("/login")
    public String login() {
        return "candidate/login";
    }

    @PostMapping("/signIn")
    public String signIn(RedirectAttributes redirectAttributes, HttpSession session, String username, String password) {
        try {

            var token = this.candidateService.login(username, password);

            var grants = token
                    .getRoles()
                    .stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toString().toUpperCase()))
                    .toList();

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(null, null, grants);
            auth.setDetails(token.getAccess_token());
            SecurityContextHolder.getContext().setAuthentication(auth);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
            session.setAttribute("token", token);

            return "redirect:/candidate/profile";
        } catch (HttpClientErrorException e) {
            redirectAttributes.addFlashAttribute("error_message", "Usuário/Senha incorretos!");
            return "redirect:/candidate/login";
        }
    }

    @GetMapping("/profile")
    @PreAuthorize("hasRole('CANDIDATE')")
    public String profile(Model model) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            var token = authentication.getDetails().toString();
            var user = this.profileCandidateService.execute(token);

            model.addAttribute("user", user);

            return "candidate/profile";
        } catch (HttpClientErrorException.Unauthorized ex) {
            return "redirect:candidate/login";
        }
    }

    @GetMapping("/jobs")
    @PreAuthorize("hasRole('CANDIDATE')")
    public String jobs(Model model, String filter) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            var token = authentication.getDetails().toString();

            if (filter != null) {
                var jobs = findJobService.execute(token, filter);
                model.addAttribute("jobs", jobs);
            }

            return "candidate/jobs";
        } catch (HttpClientErrorException.Unauthorized ex) {
            return "redirect:candidate/login";
        }
    }

    @PostMapping("/jobs/apply")
    @PreAuthorize("hasRole('CANDIDATE')")
    public String applyJob(@RequestParam("jobId") UUID jobId) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            var token = authentication.getDetails().toString();

            this.applyJobService.execute(token, jobId);
            return "redirect:candidate/jobs";
        } catch (HttpClientErrorException.Unauthorized ex) {
            return "redirect:candidate/login";
        }
    }
}

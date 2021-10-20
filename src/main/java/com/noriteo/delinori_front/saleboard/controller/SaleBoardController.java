package com.noriteo.delinori_front.saleboard.controller;

import com.noriteo.delinori_front.saleboard.dto.PageRequestDTO;
import com.noriteo.delinori_front.saleboard.dto.SaleBoardDTO;
import com.noriteo.delinori_front.saleboard.service.SaleBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/saleboard")
public class SaleBoardController {

    private final SaleBoardService saleBoardService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {

        //model.addAttribute("responseDTO", saleBoardService.getList(pageRequestDTO));
        model.addAttribute("responseDTO", saleBoardService.getListWithReplyCount(pageRequestDTO));

    }

    @GetMapping("/register")
    public void register() {

    }

    @PostMapping("/register")
    public String registerPOST(SaleBoardDTO saleBoardDTO, RedirectAttributes redirectAttributes) {

        Long sno = saleBoardService.register(saleBoardDTO);

        redirectAttributes.addFlashAttribute("result", sno);

        return "redirect:/saleboard/list";

    }

    @GetMapping("/read")
    public void read(Long sno, PageRequestDTO pageRequestDTO, Model model) {

        model.addAttribute("dto", saleBoardService.read(sno));

    }

    @PostMapping("/modify")
    public String modify(SaleBoardDTO saleBoardDTO, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes) {

        log.info("-----------------------");
        log.info(saleBoardDTO);

        redirectAttributes.addAttribute("sno", saleBoardDTO.getSno());
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());

        if(pageRequestDTO.getTypes() != null) {
            redirectAttributes.addAttribute("type", pageRequestDTO.getType());
            redirectAttributes.addAttribute("size", pageRequestDTO.getKeyword());
        }

        return "redirect:/saleboard/read";

    }

    @PostMapping("/remove")
    public String remove(Long sno, RedirectAttributes redirectAttributes) {

        //if(saleBoardService.remove(sno)) {

        return null;

    }

}

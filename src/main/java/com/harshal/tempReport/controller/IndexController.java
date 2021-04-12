package com.harshal.tempReport.controller;

import com.harshal.tempReport.model.ToDo;
import com.harshal.tempReport.repository.ToDoRepo;
import com.harshal.tempReport.utility.ExcelGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
public class IndexController {

    @Autowired
    private ToDoRepo toDoRepo;

    @GetMapping("/")
    public ModelAndView home() {
        toDoRepo.getAllTodos();
        return new ModelAndView("home");
    }

    @PostMapping("/")
    public ModelAndView postHome(
            @RequestParam("reportType") String reportType,
            @RequestParam("report1DatePicker") String report1DatePicker,
            @RequestParam("report2StartDate") String report2StartDate,
            @RequestParam("report2EndDate") String report2EndDate,
            @RequestParam("report3StartNumber") String report3StartNumber,
            @RequestParam("report3EndNumber") String report3EndNumber
    ) {
        log.info(reportType);
        if (reportType.equals("1")) {
            log.info(report1DatePicker);
        } else if (reportType.equals("2")) {
            log.info("Report2StartDate : {} Report2EndDate : {}", report2StartDate, report2EndDate);
        } else if (reportType.equals("3")) {
            log.info("report3StartNumber : {} report3EndNumber : {}", report3StartNumber, report3EndNumber);
        } else {
            log.info("INVALID DATA");
        }
        return new ModelAndView("download");
    }

    @GetMapping(value = "/download/rep1.xlsx")
    public ResponseEntity<InputStreamResource> downloadReport() throws IOException {
        List<ToDo> todos = toDoRepo.getAllTodos();
        ByteArrayInputStream in = ExcelGenerator.dataToExcel(todos);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=rep1.xlsx");

        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }
}

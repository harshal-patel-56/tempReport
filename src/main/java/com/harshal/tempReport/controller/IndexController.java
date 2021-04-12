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
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public ResponseEntity<InputStreamResource> postHome(
            @RequestParam("reportType") String reportType,
            @RequestParam("report1DatePicker") String report1DatePicker,
            @RequestParam("report2StartDate") String report2StartDate,
            @RequestParam("report2EndDate") String report2EndDate,
            @RequestParam("report3StartNumber") String report3StartNumber,
            @RequestParam("report3EndNumber") String report3EndNumber
    ) throws IOException, ParseException {
        log.info(reportType);
        if (reportType.equals("1")) {
            log.info(report1DatePicker);
            return downloadReport(report1DatePicker);
        } else if (reportType.equals("2")) {
            log.info("Report2StartDate : {} Report2EndDate : {}", report2StartDate, report2EndDate);
        } else if (reportType.equals("3")) {
            log.info("report3StartNumber : {} report3EndNumber : {}", report3StartNumber, report3EndNumber);
        } else {
            log.info("INVALID DATA");
        }
        return ResponseEntity.badRequest().build();
    }


    public ResponseEntity<InputStreamResource> downloadReport(String dateString) throws IOException, ParseException {

        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString + " 23:59:59");
        log.info("From downloadReport : {}", date.toString());
        List<ToDo> todos = toDoRepo.getReport1(date);
        ByteArrayInputStream in = ExcelGenerator.dataToExcel(todos);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=rep1.xlsx");

        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }
}

package com.project.TalonMillwork.controllers;

import com.project.TalonMillwork.entities.Report;
import com.project.TalonMillwork.entities.ReportDao;
import com.project.TalonMillwork.entities.Report_postImage;
import com.project.TalonMillwork.entities.Report_preImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/report")
public class ReportController {
    private ReportDao reportDao;

    @Autowired
    public void setReportDao(@Qualifier("Reports") ReportDao reportDao) {
        this.reportDao = reportDao;
    }

    @GetMapping
    public List<Report> getAllReports() {
        return reportDao.getAllReports();
    }

    @GetMapping(path = "{pid}")
    public List<Report> getProjectReports(@PathVariable("pid") int pid) {
        List<Report> reports = reportDao.getProjectReports(pid);
        reports.sort(new ReportComparator());
        return reports;
    }

    @DeleteMapping(path = "{rid}")
    public void deleteReport(@PathVariable("rid") int rid) {
        Optional<Report> optionalReport = reportDao.getReportByRid(rid);
        if (optionalReport.isPresent()) {
            Report report = optionalReport.get();
            if (!reportDao.deleteReport(report)) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Report Could Not Be Deleted");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Report Not Found");
        }
    }

    @PostMapping
    @ResponseBody
    public int addReport(@RequestBody Report report) {
        if (!reportDao.addReport(report)) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Report Could Not Be Added");
        }
        return report.getRid();
    }

    @PostMapping(path = "subtrade")
    public void addSubtrade(@RequestParam(value = "rid") int rid, @RequestParam("name") String name) {
        if (!reportDao.addSubtrade(rid, name)) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Report Sub-Trade Could Not Be Added");
        }
    }

    @GetMapping(path = "subtrade")
    public List<String> getSubtradeNames(@RequestParam(value = "rid") int rid) {
        return reportDao.getReportSubtradeNames(rid);
    }

    @PostMapping(path = "installer")
    public void addInstaller(@RequestParam(value = "rid") int rid, @RequestParam("name") String name) {
        if (!reportDao.addInstaller(rid, name)) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Report Installer Could Not Be Added");
        }
    }

    @GetMapping(path = "installer")
    public List<String> getInstallerIds(@RequestParam(value = "rid") int rid) {
        return reportDao.getReportInstallerNames(rid);
    }

    @PostMapping("image")
    public void uploadImage(@RequestParam(value = "rid") int rid, @RequestParam boolean pre, @RequestParam("imageFile")MultipartFile imageFile) {
        if(pre) {
            Report_preImage reportPreImage = new Report_preImage();
            reportPreImage.setRid(rid);
            try {
                if (imageFile.isEmpty()) {
                    throw new IOException();
                }
                reportPreImage.setImage(imageFile.getBytes());
                if (!reportDao.saveReportImage(reportPreImage)) {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Report Image Save Error");
                }
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image File Error");
            }
        }
        else{
            Report_postImage reportPostImage = new Report_postImage();
            reportPostImage.setRid(rid);
            try {
                if (imageFile.isEmpty()) {
                    throw new IOException();
                }
                reportPostImage.setImage(imageFile.getBytes());
                if (!reportDao.saveReportImage(reportPostImage)) {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Report Image Save Error");
                }
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image File Error");
            }
        }
    }

    @PostMapping("images")
    public void uploadMultipleFiles(@RequestParam(value="rid")int rid, @RequestParam("pre") boolean pre, @RequestParam("images") MultipartFile[] imageFiles) {
        Arrays.asList(imageFiles)
                .stream().forEach(image -> uploadImage(rid, pre, image));
    }

    @GetMapping(value = "image")
    public List<Integer> getImageIds(@RequestParam(value = "rid") int rid, @RequestParam("pre") boolean pre) {
        if(pre){
            return reportDao.getReportPreImages(rid).stream().map(Report_preImage::getImgId).collect(Collectors.toList());
        }
        return reportDao.getReportPostImages(rid).stream().map(Report_postImage::getImgId).collect(Collectors.toList());
    }

    @GetMapping(value = "image/{imgId}", produces = {"image/jpeg", "image/jpg", "image/png", "image/pdf"})
    public byte[] getImage(@PathVariable("imgId") int imgId, @RequestParam("pre") boolean pre) {
        if(pre) {
            Optional<Report_preImage> reportImage = reportDao.getReportPreImage(imgId);
            if (reportImage.isPresent()) {
                return reportImage.get().getImage();
            }
        }
        Optional<Report_postImage> reportImage = reportDao.getReportPostImage(imgId);
        if (reportImage.isPresent()) {
            return reportImage.get().getImage();
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image Not Found");
    }

    static class ReportComparator implements Comparator<Report> {
        // Used for sorting in oldest to newest
        public int compare(Report a, Report b) {
            return b.getDate().compareTo(a.getDate());
        }
    }
}

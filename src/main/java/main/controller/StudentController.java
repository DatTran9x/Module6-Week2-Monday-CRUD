package main.controller;

import com.sun.org.apache.xpath.internal.operations.Mult;
import main.model.Classroom;
import main.model.Student;
import main.service.IClassroomService;
import main.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    IClassroomService classroomService;

    @Autowired
    IStudentService studentService;

    @GetMapping("home")
    public ModelAndView homepage(){
        List<Student> list = studentService.findAll();
        ModelAndView mav = new ModelAndView("homepage");
        mav.addObject("students",list);
        return mav;
    }

    @GetMapping("create")
    public ModelAndView createForm(){
        ModelAndView mav = new ModelAndView("create");
        List<Classroom> list = classroomService.findAll();
        Student student = new Student();
        mav.addObject("student",student);
        mav.addObject("list",list);
        return mav;
    }

    @PostMapping("create")
    public ModelAndView create(@ModelAttribute(value = "student") Student student, @RequestParam Long idClassroom, @RequestParam MultipartFile upImg){
        Classroom newClassroom = new Classroom();
        newClassroom.setId(idClassroom);
        student.setClassroom(newClassroom);
        String nameFile = upImg.getOriginalFilename();
        try {
            FileCopyUtils.copy(upImg.getBytes(),new File("C:\\HocTap\\Module4\\BaiTap\\Week2\\CRUD\\src\\main\\webapp\\img\\"+nameFile));
            student.setImg("/img/"+nameFile);
            studentService.save(student);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("edit/{id}")
    public ModelAndView editForm(@PathVariable Long id){
        Student student = studentService.findById(id);
        List<Classroom> list = classroomService.findAll();
        ModelAndView mav = new ModelAndView("edit");
        mav.addObject("student",student);
        mav.addObject("list",list);
        return mav;
    }

    @PostMapping("edit/{id}")
    public ModelAndView edit(@ModelAttribute(value = "student") Student student, @RequestParam MultipartFile upImg,@RequestParam Long idClassroom){
        Classroom classroom = new Classroom();
        classroom.setId(idClassroom);
        student.setClassroom(classroom);
        if(upImg.getSize()!=0){
            File oldFile = new File("C:\\HocTap\\Module4\\BaiTap\\Week2\\CRUD\\src\\main\\webapp"+student.getImg());
            oldFile.delete();
            String nameFile = upImg.getOriginalFilename();
            try {
                FileCopyUtils.copy(upImg.getBytes(),new File("C:\\HocTap\\Module4\\BaiTap\\Week2\\CRUD\\src\\main\\webapp\\img\\"+nameFile));
                student.setImg("/img/"+nameFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        studentService.save(student);
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("delete/{id}")
    public ModelAndView deleteForm(@PathVariable Long id){
        Student student = studentService.findById(id);
        ModelAndView mav = new ModelAndView("delete");
        mav.addObject("student",student);
        return mav;
    }

    @PostMapping("delete/{id}")
    public ModelAndView delete(@PathVariable Long id,@ModelAttribute Student student){
        File oldFile = new File("C:\\HocTap\\Module4\\BaiTap\\Week2\\CRUD\\src\\main\\webapp"+student.getImg());
        oldFile.delete();
        studentService.delete(id);
        return new ModelAndView("redirect:/home");
    }
}

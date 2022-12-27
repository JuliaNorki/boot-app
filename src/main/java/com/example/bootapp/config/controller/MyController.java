package com.example.bootapp.config.controller;

import com.example.bootapp.config.model.User;
import com.example.bootapp.config.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


    //Spring MVC + CRUD


    @Controller //помечает класс контроллером ( в этом классе сможем обрабатывать поступающие от пользователя HTTP запросы.
    @RequestMapping("/users") //Это одна из основных аннотаций в Spring,
    // которая сопоставляет HTTP-запросы (URL-адреса) с методами:
    public class MyController {

        private final UserService userService;

        @Autowired //внедрение зависимостей
        MyController(UserService userService) {
            this.userService = userService;
        }

        @GetMapping() //Аннотация для сопоставления HTTP GET-запросов с конкретными методами обработки.
        // В частности, @GetMappingэто составная аннотация, которая действует как ярлык для @RequestMapping(method = RequestMethod.GET).
        public String getAllUsers(Model model) {
            model.addAttribute("users", userService.getAllUsers());
            return "index";
        }


        @GetMapping("/{id}")
        public String show(@PathVariable("id") int id, Model model) {
            model.addAttribute("user", userService.getUser(id));
            return "show";
        }

        @GetMapping("/new")
        public String addUser(@ModelAttribute("user") User user) {
            return "new";
        }

        @PostMapping() //Аннотация для сопоставления HTTP POST-запросов с конкретными методами обработки.
        // В частности, @PostMappingэто составная аннотация,
        // которая действует как ярлык для @RequestMapping(method = RequestMethod.POST).
        public String create(@ModelAttribute("user") User user) {
            userService.save(user);
            return "redirect:/users";
        }

        @GetMapping("/{id}/edit")
        public String edit(Model model, @PathVariable("id") int id) {
            model.addAttribute("user", userService.getUser(id));
            return "edit";
        }

        @PatchMapping("/{id}")
        public String update(@ModelAttribute("user") User user, //С помощью этой аннотации мы можем, что-то извлечь из HTTP запроса.
                             // Например можно взять с помощью этой аннотации user-a и сохранить в Базу данных
                             @PathVariable("id") int id) { //@PathVariable можно использовать для обработки переменных шаблона
            // в сопоставлении URI запроса и установки их в качестве параметров метода.
            userService.updateUser(id, user);
            return "redirect:/users";
        }

        @DeleteMapping("/{id}")
        public String delete(@PathVariable("id") int id) {
            userService.deleteUser(id);
            return "redirect:/users";
        }
    }


package com.web.web.controllers;

import com.web.web.domain.TestEntity;

import com.web.web.repo.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Controller
public class HomeController  {
    @Autowired
    private Repository repository;

    @GetMapping(value = "/")
    public String home(Model model) throws IOException {
        return "index";
    }


    }



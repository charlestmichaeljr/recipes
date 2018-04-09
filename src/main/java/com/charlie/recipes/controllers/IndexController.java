package com.charlie.recipes.controllers;

import com.charlie.recipes.domain.Category;
import com.charlie.recipes.domain.Recipe;
import com.charlie.recipes.domain.UnitOfMeasure;
import com.charlie.recipes.repositories.CategoryRepository;
import com.charlie.recipes.repositories.UnitOfMeasureRepository;
import com.charlie.recipes.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.Set;

@Controller
public class IndexController {


    private RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","/index"})
    public String getIndexPage(Model model){
        Set<Recipe> recipeSet
                = recipeService.getRecipes();
        model.addAttribute("recipes",recipeSet);
        return "index";
    }
}

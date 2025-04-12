package br.com.composer.composer.controller;

import br.com.composer.composer.entity.Genre;
import br.com.composer.composer.services.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    // ========== MÉTODOS PARA A INTERFACE WEB ==========

    @GetMapping
    public String listGenres(Model model) {
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("title", "Genres List");
        return "genres/list";
    }

    @GetMapping("/new")
    public String showGenreForm(Model model) {
        model.addAttribute("genre", new Genre());
        model.addAttribute("title", "New Genre");
        return "genres/form";
    }

    @PostMapping("/save")
    public String saveGenre(@ModelAttribute Genre genre, RedirectAttributes redirectAttributes) {
        Genre savedGenre = genreService.save(genre);
        redirectAttributes.addFlashAttribute("successMessage", 
            "Genre '" + savedGenre.getName() + "' saved successfully!");
        return "redirect:/genres";
    }

    @GetMapping("/edit/{id}")
    public String editGenre(@PathVariable Integer id, Model model) {
        Genre genre = genreService.findById(id);
        model.addAttribute("genre", genre);
        model.addAttribute("title", "Edit Genre");
        return "genres/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteGenre(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Genre genre = genreService.findById(id);
        genreService.delete(id);
        redirectAttributes.addFlashAttribute("successMessage", 
            "Genre '" + genre.getName() + "' deleted successfully!");
        return "redirect:/genres";
    }

    @GetMapping("/popular")
    public String showPopularGenres(Model model) {
        model.addAttribute("genres", genreService.findPopularGenres());
        model.addAttribute("title", "Popular Genres");
        return "genres/list";
    }

    // ========== MÉTODOS DA API (REST) ==========

    @GetMapping("/api")
    @ResponseBody
    public List<Genre> getAllGenresApi() {
        return genreService.findAll();
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public Genre getGenreByIdApi(@PathVariable Integer id) {
        return genreService.findById(id);
    }

    @PostMapping("/api")
    @ResponseBody
    public Genre createGenreApi(@RequestBody Genre genre) {
        return genreService.save(genre);
    }

    @PutMapping("/api/{id}")
    @ResponseBody
    public Genre updateGenreApi(@PathVariable Integer id, @RequestBody Genre genre) {
        return genreService.update(id, genre);
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public void deleteGenreApi(@PathVariable Integer id) {
        genreService.delete(id);
    }

    @GetMapping("/api/popular")
    @ResponseBody
    public List<Genre> getPopularGenresApi() {
        return genreService.findPopularGenres();
    }
}
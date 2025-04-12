package br.com.composer.composer.controller;

import br.com.composer.composer.entity.Artist;
import br.com.composer.composer.services.ArtistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    // ========== MÉTODOS PARA A INTERFACE WEB ==========

    @GetMapping
    public String listArtists(Model model) {
        model.addAttribute("artists", artistService.findAll());
        model.addAttribute("title", "Artists List");
        return "artists/list";
    }

    @GetMapping("/new")
    public String showArtistForm(Model model) {
        model.addAttribute("artist", new Artist());
        model.addAttribute("title", "New Artist");
        return "artists/form";
    }

    @PostMapping("/save")
    public String saveArtist(@ModelAttribute Artist artist, RedirectAttributes redirectAttributes) {
        Artist savedArtist = artistService.save(artist);
        redirectAttributes.addFlashAttribute("successMessage", "Artist saved successfully!");
        return "redirect:/artists";
    }

    @GetMapping("/edit/{id}")
    public String editArtist(@PathVariable Integer id, Model model) {
        Artist artist = artistService.findById(id);
        model.addAttribute("artist", artist);
        model.addAttribute("title", "Edit Artist");
        return "artists/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteArtist(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        artistService.delete(id);
        redirectAttributes.addFlashAttribute("successMessage", "Artist deleted successfully!");
        return "redirect:/artists";
    }

    // ========== MÉTODOS DA API (REST) ==========
    // (Mantidos para compatibilidade com clientes API)

    @GetMapping("/api")
    @ResponseBody
    public List<Artist> getAllArtistsApi() {
        return artistService.findAll();
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public Artist getArtistByIdApi(@PathVariable Integer id) {
        return artistService.findById(id);
    }

    @PostMapping("/api")
    @ResponseBody
    public Artist createArtistApi(@RequestBody Artist artist) {
        return artistService.save(artist);
    }

    @PutMapping("/api/{id}")
    @ResponseBody
    public Artist updateArtistApi(@PathVariable Integer id, @RequestBody Artist artist) {
        return artistService.update(id, artist);
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public void deleteArtistApi(@PathVariable Integer id) {
        artistService.delete(id);
    }

    @GetMapping("/api/search")
    @ResponseBody
    public List<Artist> searchArtistsApi(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String type) {
        return artistService.search(name, type);
    }
}
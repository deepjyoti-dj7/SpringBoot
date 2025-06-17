package com.dj.journalApp.controller;

import com.dj.journalApp.entity.JournalEntry;
import com.dj.journalApp.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public JournalEntryService getAll() {
        return journalEntryService;
    }

    @GetMapping("/id/{id}")
    public JournalEntry getJournalEntryById(@PathVariable long id) {
        return null;
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry) {
        journalEntryService.saveEntry(myEntry);
        return true;
    }

    @DeleteMapping("/id/{id}")
    public boolean deleteJournalEntryById(@PathVariable long id) {
        return true;
    }

    @PutMapping("/id/{id}")
    public boolean updateJournalEntryById(@PathVariable long id, @RequestBody JournalEntry myEntry) {
        return true;
    }
}

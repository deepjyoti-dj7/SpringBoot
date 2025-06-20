package com.dj.journalApp.controller;

import com.dj.journalApp.entity.JournalEntry;
import com.dj.journalApp.entity.User;
import com.dj.journalApp.service.JournalEntryService;
import com.dj.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<JournalEntry>> getAllJournalEntries() {
        List<JournalEntry> allEntries = journalEntryService.getAll();
        if (allEntries != null && !allEntries.isEmpty()) {
            return new ResponseEntity<>(allEntries, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("{userName}")
    public ResponseEntity<List<JournalEntry>> getAllJournalEntriesByUser(@PathVariable String userName) {
        User user = userService.findByUserName(userName);
        List<JournalEntry> allEntries = user.getJournalEntries();
        if (allEntries != null && !allEntries.isEmpty()) {
            return new ResponseEntity<>(allEntries, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId id) {
        Optional<JournalEntry> journalEntry = journalEntryService.findById(id);
        if (journalEntry.isPresent()) {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String userName) {
        try {
            journalEntryService.saveEntry(myEntry, userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId id) {
        journalEntryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/id/{userName}/{id}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId id, @PathVariable String userName) {
        journalEntryService.deleteById(id, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{userName}/{id}")
    public ResponseEntity<JournalEntry> updateJournalEntryById(
            @PathVariable ObjectId id,
            @RequestBody JournalEntry newEntry,
            @PathVariable String userName
    ) {
        JournalEntry oldEntry = journalEntryService.findById(id).orElse(null);
        if (oldEntry != null) {
            oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : oldEntry.getContent());
            journalEntryService.saveEntry(oldEntry, userName);
            return new ResponseEntity<JournalEntry>(oldEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

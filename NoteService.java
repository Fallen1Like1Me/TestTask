package service;

import lombok.RequiredArgsConstructor;
import model.Note;
import org.springframework.stereotype.Service;
import repository.NoteRepository;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final UserService userService;

    public void save(Note note) {
        noteRepository.save(note);
    }

    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    public List<Note> findByUserName(String name) {
        return userService.getByUsername(name).getNote();
    }

    public String getContent(Note note) {
        return note.getContent();
    }
}

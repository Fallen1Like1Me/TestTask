package service;

import lombok.RequiredArgsConstructor;
import model.Note;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    @Lazy
    @Autowired
    private UserRepository repository;
    private NoteService noteService;


        public User save(User user) {
            return repository.save(user);
        }



        public User create(User user) {
            if (repository.existsByUsername(user.getUsername())) {
                // Заменить на свои исключения
                throw new RuntimeException("Пользователь с таким именем уже существует");
            }

            if (repository.existsByEmail(user.getEmail())) {
                throw new RuntimeException("Пользователь с таким email уже существует");
            }

            return save(user);
        }


        public User getByUsername(String username) {
            return repository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

        }


        public UserDetailsService userDetailsService() {
            return this::getByUsername;
        }


        public User getCurrentUser() {
            var username = SecurityContextHolder.getContext().getAuthentication().getName();
            return getByUsername(username);
        }

        public String getContentByUserName(String username) {
            return noteService.getContent((Note) getByUsername(username).getNote());
        }
}


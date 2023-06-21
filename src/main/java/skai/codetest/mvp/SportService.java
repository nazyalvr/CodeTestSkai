package skai.codetest.mvp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import skai.codetest.mvp.repository.TournamentRepository;

@Service
@AllArgsConstructor
public abstract class SportService {
    private final TournamentRepository tournamentRepository;
}

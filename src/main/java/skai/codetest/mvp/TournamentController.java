package skai.codetest.mvp;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import skai.codetest.mvp.game.Game;
import skai.codetest.mvp.repository.TournamentRepository;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@RestController
@AllArgsConstructor
public class TournamentController {
    private final CsvParser csvParser;
    private final TournamentRepository tournamentRepository;

    @PostMapping("/get-mvp")
    public Map.Entry<String, Long> getMvpPlayer(@RequestParam("files") MultipartFile[] files)
            throws FileFormatException, IOException, FileExtensionException {
        for (MultipartFile file : files) {
            Game game = csvParser.processFile(file);
            tournamentRepository.updateResults(game.getPlayersGamePoints());
        }
        if (!Objects.equals(tournamentRepository.getMvpPlayer().getKey(), "")) {
            return tournamentRepository.getMvpPlayer();
        } else {
            throw new FileFormatException("Can't find MVP Player");
        }
    }
}

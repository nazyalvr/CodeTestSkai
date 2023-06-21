package skai.codetest.mvp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import skai.codetest.mvp.repository.TournamentRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TournamentControllerTest {

    @Test
    public void tournamentTest() throws FileFormatException, IOException, FileExtensionException {
        Path path = Paths.get("D:\\codetest\\mvp\\src\\main\\java\\skai\\codetest\\mvp\\tournament\\game.csv");
        String name = "game.csv";
        String originalFileName = "game.csv";
        String contentType = "text/plain";
        byte[] content;
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
            throw new FileFormatException("Bad file");
        }
        MultipartFile result = new MockMultipartFile(name,
                originalFileName, contentType, content);

        MultipartFile[] mp = new MultipartFile[1];
        mp[0] = result;
        TournamentController tc = new TournamentController(new CsvParser(), new TournamentRepository());
        assertEquals(tc.getMvpPlayer(mp), Map.entry("nick4", 50L));
    }

    @Test
    public void tournamentTestExtensionException() throws FileFormatException {
        Path path = Paths.get("D:\\codetest\\mvp\\src\\main\\java\\skai\\codetest\\mvp\\tournament\\game.txt");
        String name = "game.txt";
        String originalFileName = "game.txt";
        String contentType = "text/plain";
        byte[] content;
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
            throw new FileFormatException("Bad file");
        }
        MultipartFile result = new MockMultipartFile(name,
                originalFileName, contentType, content);

        FileExtensionException thrown = Assertions.assertThrows(FileExtensionException.class, () -> {
            MultipartFile[] mp = new MultipartFile[1];
            mp[0] = result;
            TournamentController tc = new TournamentController(new CsvParser(), new TournamentRepository());
            tc.getMvpPlayer(mp);
        });
        Assertions.assertEquals("Wrong file extension", thrown.getMessage());
    }
}
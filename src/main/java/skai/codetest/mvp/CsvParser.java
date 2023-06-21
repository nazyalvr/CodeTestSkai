package skai.codetest.mvp;

import lombok.AllArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import skai.codetest.mvp.game.BasketballGame;
import skai.codetest.mvp.game.Game;
import skai.codetest.mvp.game.GameType;
import skai.codetest.mvp.game.HandballGame;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;

@Component
@AllArgsConstructor
public class CsvParser {
    public Game processFile(MultipartFile file) throws IOException, FileFormatException, FileExtensionException{
        Game game;
        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (!Objects.equals("csv", fileExtension)){
            throw new FileExtensionException("Wrong file extension");
        }
        String content = new String(file.getBytes());
        if (content.isEmpty()){
            throw new FileFormatException("File is empty!");
        }
        String[] lines = content.split("\\r?\\n");

        GameType gameType = GameType.valueOf(lines[0]);

        switch (gameType) {
            case BASKETBALL -> {
                BasketballGame basketballGame = new BasketballGame(new HashSet<>());
                basketballGame.parse(lines);
                return basketballGame;
            }
            case HANDBALL -> {
                HandballGame handballGame = new HandballGame(new HashSet<>());
                handballGame.parse(lines);
                return handballGame;
            }
            default -> throw new FileFormatException("Unsupported game type!");
        }
    }
}

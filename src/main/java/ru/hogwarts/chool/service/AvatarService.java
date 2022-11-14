package ru.hogwarts.chool.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.chool.exception.AvatarNotFoundException;
import ru.hogwarts.chool.model.Avatar;
import ru.hogwarts.chool.repository.AvatarRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class AvatarService {

    private final AvatarRepository avatarRepository;

    @Value("./avatar")
    private String folderForAvatars;

    public AvatarService(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    public void uploadAvatar(MultipartFile multipartFile) throws IOException {
        byte[] data = multipartFile.getBytes();
        Avatar avatar = create(multipartFile.getSize(), multipartFile.getContentType(), data);

        String extension = Optional.ofNullable(multipartFile.getOriginalFilename()).
                map(s -> s.substring(multipartFile.getOriginalFilename().lastIndexOf('.'))).
                orElse("");
        Path path = Paths.get(folderForAvatars).resolve(avatar.getId() + extension);
        Files.write(path, data);
        avatar.setFilePath(path.toString());
        avatarRepository.save(avatar);
    }

    private Avatar create(long size, String contentType, byte[] data) {
        Avatar avatar = new Avatar();
        avatar.setDate(data);
        avatar.setFileSize(size);
        avatar.setMediaType(contentType);
        return avatarRepository.save(avatar);
    }

    public Pair<String, byte[]> readAvatarFromDb(long id) {
        Avatar avatar = avatarRepository.findById(id).orElseThrow(() -> new AvatarNotFoundException(id));
        return Pair.of(avatar.getMediaType(), avatar.getDate());
    }

    public Pair<String, byte[]> readAvatarFromFs(long id) throws IOException {
        Avatar avatar = avatarRepository.findById(id).orElseThrow(() -> new AvatarNotFoundException(id));
        return Pair.of(avatar.getMediaType(), Files.readAllBytes(Paths.get(avatar.getFilePath())));
    }
}

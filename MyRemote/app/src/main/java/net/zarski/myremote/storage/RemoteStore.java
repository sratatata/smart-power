package net.zarski.myremote.storage;

import com.google.gson.Gson;

import net.zarski.myremote.core.Remote;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created by lb_lb on 06.01.17.
 */
public class RemoteStore {

    private FileStorage fileStorage;

    public RemoteStore(FileStorage fileStorage) {
        this.fileStorage = fileStorage;
    }

    public void save(Remote remote) throws IOException {
        Gson gson = new Gson();
        fileStorage.saveString(gson.toJson(remote));

    }

    public Remote load() throws IOException {
        InputStream is = fileStorage.loadFile();
        Gson gson = new Gson();
        Reader reader = new InputStreamReader(is, "UTF-8");
        return new Gson().fromJson(reader, Remote.class);
    }
}

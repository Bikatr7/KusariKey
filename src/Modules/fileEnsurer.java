package Modules;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;

public class fileEnsurer 
{
    private Path configDir;
    private Path credentialsDir;
    private Path credentialsFile;

    public fileEnsurer()
    {

        String userHome = System.getProperty("user.home");

        configDir = Paths.get(userHome, "KusariKeyConfig");

        setupBaseDirectories();

    }

    private void setupBaseDirectories()
    {

        credentialsDir = this.configDir.resolve("credentials");

        setupCredentialsDirectory();
    }

    private void setupCredentialsDirectory()
    {
        credentialsFile = this.credentialsDir.resolve("credentials.txt");
    } 
    
}
package Modules;

// built-in libraries
import java.nio.file.Path;
import java.nio.file.Files;

import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;
import java.util.ArrayList;

//-------------------start-of-fileHandler---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

public class FileUtil 
{
    private static Path currentFilePath;

    private static FileWriter writer;
    private static Scanner reader;


//-------------------start-of-fileHandler()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    private FileUtil()
    {
        throw new UnsupportedOperationException("This is a static class and cannot be instantiated.");
    }

//-------------------start-of-getCurrentFilePath()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the current file path.
     * @return currentFIlePath Path - The current file path.
     */

    public static Path getCurrentFilePath()
    {
        return currentFilePath;
    }

//-------------------start-of-standardCreateDirectory()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Creates a directory if it doesn't exist. Also logs the action.
     * @param directoryPath Path - The path of the directory to create.
     * @throws IOException
     */

    public static void standardCreateDirectory(Path directoryPath) throws IOException
    {
        if(!directoryPath.toFile().exists())
        {
            directoryPath.toFile().mkdirs();
            Logger.logAction(directoryPath.toString() + " was created due to the lack of it's existence.");
        }
    }


//-------------------start-of-standardCreateFile()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Creates a file if it doesn't exist. Also logs the action.
     * @param filePath Path - The path of the file to create.
     * @throws IOException
     */

    public static void standardCreateFile(Path filePath) throws IOException
    {
        if(Files.notExists(filePath))
        {
            filePath.toFile().createNewFile();
            Logger.logAction(filePath.toString() + " was created due to the lack of it's existence.");
        }
    }

//-------------------start-of-modifiedCreateFile()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Creates a file if it doesn't exist or if the file is blank. Also logs the action.
     * @param filePath Path - The path of the file to create.
     * @throws IOException
     */

    public static void modifiedCreateFile(Path filePath) throws IOException
    {
        if(Files.notExists(filePath) || filePath.toFile().length() == 0)
        {
            filePath.toFile().createNewFile();
            Logger.logAction(filePath.toString() + " was created due to the lack of it's existence.");
        }
    }

//-------------------start-of-switchToFile()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Switches the current file to the file specified by the filePath parameter.
     * @param filePath  Path - The path of the file to switch to.
     * @throws IOException
     * @throws IllegalArgumentException
     */

    public static void switchToFile(Path filePath) throws IOException, IllegalArgumentException
    {

        if (filePath == null) 
        {
            throw new IllegalArgumentException("Provided filePath is null");
        }

        currentFilePath = filePath;

        if(writer != null)
        {
            writer.close();
        }

        if(reader != null)
        {
            reader.close();
        }

        reader = new Scanner(Files.newInputStream(currentFilePath));
        writer = new FileWriter(currentFilePath.toString(), true);

        Logger.logAction("Switched to file: " + currentFilePath.toString());
    }


//-------------------start-of-writeSeiLine()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Writes a line to the current file.
     * Current path is switched back to the previous file after the line is written.
     * @param path_to_write_to Path - The path of the file to write to.
     * @param items_to_write E[] - The items to write to the file.
     * @throws IOException
     */

    public static <E> void writeSeiLine(Path path_to_write_to, E[] items_to_write) throws IOException
    {
        String line_to_write = "";
        Path oldPath = currentFilePath;

        for(E item : items_to_write)
        {
            line_to_write += item.toString() + ",";
        }

        switchToFile(path_to_write_to);

        writer.write(line_to_write + "\n");

        if(oldPath != null)
            switchToFile(oldPath);

    }

//-------------------start-of-editSeiLine()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Edits a line in the current file.
     * Current path is not altered.
     * @param path_to_write_to Path - The path of the file to write to.
     * @param target_line int - The line to edit.
     * @param column_number int - The column to edit.
     * @param value_to_replace_to E - The value to replace the current value with.
     * @throws IOException
     */

    public static <E> void editSeiLine(Path path_to_write_to, int target_line, int column_number, E value_to_replace_to) throws IOException
    {

        ArrayList<String> lines = new ArrayList<String>();

        String line_to_edit;

        String [] items;

        lines = (ArrayList<String>) Files.readAllLines(path_to_write_to, java.nio.charset.StandardCharsets.UTF_8);

        line_to_edit = lines.get(target_line - 1);
        items = line_to_edit.split(",");

        items[column_number - 1] = value_to_replace_to.toString();

        line_to_edit = "";

        for(String item : items)
        {
            line_to_edit += item + ",";
        }

        lines.set(target_line - 1, line_to_edit);

        Files.write(path_to_write_to, lines, java.nio.charset.StandardCharsets.UTF_8);

    }

//-------------------start-of-readSeiLine()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Reads from a file.
     * Current path is not altered.
     * @param path_to_read_from Path - The path of the file to read from.
     * @param target_line int - The line to read.
     * @param column int - The column to read.
     * @return String - The value of the item in the specified column.
     * @throws IOException
     */

    public static String readSeiLine(Path path_to_read_from, int target_line, int column) throws IOException, IndexOutOfBoundsException
    {
        String line_to_read = "";

        String [] items;

        line_to_read = Files.readAllLines(path_to_read_from, java.nio.charset.StandardCharsets.UTF_8).get(target_line - 1);
        items = line_to_read.split(",");

        return items[column - 1];
    }

//-------------------start-of-deleteSeiLine()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Deletes a line from a file.
     * Current path is not altered.
     * @param path_to_delete_from Path - The path of the file to delete from.
     * @param target_line int - The line to delete.
     * @throws IOException
     */

    public static void deleteSeiLine(Path path_to_delete_from, int target_line) throws IOException
    {
        ArrayList<String> lines = new ArrayList<String>();

        lines = (ArrayList<String>) Files.readAllLines(path_to_delete_from, java.nio.charset.StandardCharsets.UTF_8);

        lines.remove(target_line - 1);

        Files.write(path_to_delete_from, lines, java.nio.charset.StandardCharsets.UTF_8);
    }

//-------------------start-of-deleteAllOccurrencesOfID()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Deletes all lines with a specified ID.
     * Current path is not altered.
     * @param path_to_delete_from Path - The path of the file to delete from.
     * @param idIndex int - The index of the ID column.
     * @param id int - The ID to delete.
     * @throws IOException
     */

    public static void deleteAllOccurrencesOfID(Path path_to_delete_from, int idIndex, int id) throws IOException
    {
        ArrayList<String> lines = new ArrayList<String>();

        lines = (ArrayList<String>) Files.readAllLines(path_to_delete_from, java.nio.charset.StandardCharsets.UTF_8);

        for(int i = 0; i < lines.size(); i++)
        {
            if(Integer.parseInt(lines.get(i).split(",")[idIndex]) == id)
            {
                lines.remove(i);
                i--;
            }
        }

        Files.write(path_to_delete_from, lines, java.nio.charset.StandardCharsets.UTF_8);
    }

} // end-of-fileHandler
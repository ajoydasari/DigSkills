package Utilities;

import org.apache.commons.io.FilenameUtils;
import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.Serializable;

import static Utilities.Driver.logger;

public class FileServiceBean implements Serializable {

    private File currentFolder;

    public void SetFolderPath(String folderPath)
    {
        currentFolder = new File(folderPath);
    }

    public void ListAllFilesInFolder()
    {
        File[] files = currentFolder.listFiles();
        String message;
//        System.out.printf("\nReading all files within the folder supplied and print the relavent File Details\n\n");
        for (File file : files) {
            message = "FileName: '"+file.getName()+"', File mime type: '"+new MimetypesFileTypeMap().getContentType(file)+"', File Size: '"+file.length()+" bytes', File Extension: '"+FilenameUtils.getExtension(file.getName())+"'";
            logger.info(message);
//            System.out.printf("FileName: '%s', \t File mime type: '%s', \tFile Size: '%d bytes', \tFile Extension: '%s' \n",
//                    file.getName(), new MimetypesFileTypeMap().getContentType(file), file.length(), FilenameUtils.getExtension(file.getName()));
        }
        logger.info(" ");
    }

    public void ListAllValidFilesInFolder()
    {
        File[] files = currentFolder.listFiles();
        String fileExtension;
        for (int i=0, j=0; i< files.length; i++) {
            fileExtension = FilenameUtils.getExtension(files[i].getName());
            if(fileExtension.equalsIgnoreCase("csv") || fileExtension.equalsIgnoreCase("xls")|| fileExtension.equalsIgnoreCase("xlsx"))
            {
                logger.info(files[i].getName());
            }
        }
        logger.info(" ");
    }


    public void ListAllInvalidFilesInFolder()
    {
        File[] files = currentFolder.listFiles();
        String fileExtension;
        for (int i=0, j=0; i< files.length; i++) {
            fileExtension = FilenameUtils.getExtension(files[i].getName());
            if(fileExtension.equalsIgnoreCase("csv") || fileExtension.equalsIgnoreCase("xls")|| fileExtension.equalsIgnoreCase("xlsx"))
            {}
            else
                logger.info(files[i].getName());
        }
        logger.info(" ");
    }

    public String[] getValidFiles()
    {
        File[] files = currentFolder.listFiles();
        String fileExtension;
        String relevantFiles = "";
        for (int i=0, j=0; i< files.length; i++) {
            fileExtension = FilenameUtils.getExtension(files[i].getName());
            if(fileExtension.equalsIgnoreCase("csv") || fileExtension.equalsIgnoreCase("xls")|| fileExtension.equalsIgnoreCase("xlsx"))
            {
                relevantFiles = relevantFiles + files[i].getName() + "/";
            }
        }

        return relevantFiles.split("/");
    }
}

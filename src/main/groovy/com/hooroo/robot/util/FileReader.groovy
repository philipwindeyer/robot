package com.hooroo.robot.util

import javax.activation.MimetypesFileTypeMap

/**
 * Provides a single method for reading a file with commands to be executed
 */
class FileReader {

    /**
     * Reads file and returns a list of commands (Strings) read from the file
     * @param filePath relative or absolute path to the file
     * @return List of commands read line by line from the file
     * @throws FileNotFoundException When the file can't be found, is not a file, is not a text file, or is empty
     */
    static List<String> read(String filePath) {

        File file = new File(filePath)

        if (!file.exists()) {
            throw new FileNotFoundException("'${filePath}' not found")
        }

        if (file.isDirectory()) {
            throw new FileNotFoundException("'${filePath}' is not a file")
        }

        if (file.size() < 1) {
            throw new FileNotFoundException("'${filePath}' is an empty file")
        }

        String mimeType = new MimetypesFileTypeMap().getContentType(file)
        Boolean text = false

        if (mimeType.startsWith("text/")) {
            text = true
        }

        if (mimeType.startsWith("application/octet-stream")) {
            text = true
        }

        if (!text) {
            throw new FileNotFoundException("'${filePath}' is not a text file")
        }

        List<String> lines = []

        file.eachLine {
            lines << it
        }

        return lines
    }
}

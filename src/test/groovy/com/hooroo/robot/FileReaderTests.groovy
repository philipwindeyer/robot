package com.hooroo.robot

import com.hooroo.robot.util.FileReader
import spock.lang.Specification

class FileReaderTests extends Specification {

    def "Should fail if the file does not exist"() {
        when:
        FileReader.read("Hello")

        then:
        Exception exception = thrown()
        exception instanceof FileNotFoundException
        exception.message == "'Hello' not found"
    }

    def "Should fail if the file path is a directory instead of a file"() {
        when:
        FileReader.read("src/")

        then:
        Exception exception = thrown()
        exception instanceof FileNotFoundException
        exception.message == "'src/' is not a file"
    }

    def "Should fail if the file is empty"() {
        when:
        FileReader.read("src/test/resources/empty.txt")

        then:
        Exception exception = thrown()
        exception instanceof FileNotFoundException
        exception.message == "'src/test/resources/empty.txt' is an empty file"
    }

    def "Should fail if the file is not plain text"() {
        when:
        FileReader.read("src/test/resources/test.jpg")

        then:
        Exception exception = thrown()
        exception instanceof FileNotFoundException
        exception.message == "'src/test/resources/test.jpg' is not a text file"
    }

    def "Should successfully read CSV file as this format is still plain text"() {
        when:
        List<String> lines = FileReader.read("src/test/resources/test.csv")

        then:
        String firstLine = lines[0]
        firstLine.contains("PLACE")
        firstLine.contains("NORTH")
        firstLine.contains(",")
    }

    def "Should successfully return an array of commands split by line break in text file"() {
        when:
        List<String> lines = FileReader.read("src/test/resources/test.txt")

        then:
        int line = 0
        new File("src/test/resources/test.txt").eachLine {
            it == lines[line++]
        }
    }

    def "Should successfully return array with single item corresponding to 1 command in text file"() {

        when:
        List<String> lines = FileReader.read("src/test/resources/test-1-line.txt")

        then:
        int line = 0
        new File("src/test/resources/test-1-line.txt").eachLine {
            it == lines[line++]
        }

        line == 1
    }
}

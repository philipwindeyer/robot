package com.hooroo.robot

/**
 * Psuedo "View" - CLI entry point for entering commands from a shell
 */
class CommandLine {

    static void main(String[] args) {
        Application instance = new Application()
        BufferedReader stdin = System.in.newReader()

        String input
        String output

        while (true) {
            input = stdin.readLine()

            if (input == null) {
                break
            }

            try {
                output = instance.execute(input)

                if (output) {
                    println(output)
                }
            } catch (Exception e) {
                if (e instanceof InterruptedException) {
                    break

                } else {
                    println(e.message)
                }
            }
        }
    }
}

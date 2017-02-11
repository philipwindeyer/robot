package com.hooroo.robot

/**
 * Psuedo "View" - CLI entry point for entering commands from a shell
 */
class CommandLine {

    // TODO Move io logic from main() into instance method, and instantiate CommandLine on startup instead
    // TODO if args.length > 0 call alternate instance method which handles file io instead (i.e. if file to be read - run those commands and exit, else accept user input from stdin)

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

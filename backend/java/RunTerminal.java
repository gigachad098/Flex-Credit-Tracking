import java.util.ArrayList;

class RunTerminal {
    public static void main(String args[]) {
        String command = "";
        if (args.length < 1) return;
        for (int i = 0; i < args.length; i++) {
            command += args[i];
            if (i < args.length - 1) command += " ";
        }
        ArrayList<String> emails = TerminalHandler.execute(command);
        for (String s : emails) {
            System.out.println("Java:\t" + s);
        }
    }
}
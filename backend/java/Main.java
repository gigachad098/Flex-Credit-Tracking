import java.util.ArrayList;

class Main {

    public static void main(String args[]) {
        ArrayList<String> output = new ArrayList<String>();

        while (output.size() == 0) {
            TerminalHandler.execute("node ..\\..\\test-app\\scrape.mjs");
            output = TerminalHandler.execute("python ..\\python\\StatAnalysis.py ..\\..\\test-app");
            if (output.get(0).equals("Try again Java!")) {
                System.out.println("Unable to fetch data... trying again...");
                output.clear();
            }
        }

        System.out.println("\nData Retrieved!\n\n--------------------------------------------------");

        for (String s : output) {
            System.out.println(s);
        }
    }

}
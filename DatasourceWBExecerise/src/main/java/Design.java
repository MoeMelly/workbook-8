import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@SuppressWarnings("unused")

public class Design {
    private final String FILE_PATH = "logs.txt";


    //Styles //
    public static void titleNewLineTop () {
        System.out.println("\n════════════════════════════════════════════════════════════════════════");
    }
    public static void titleLineBottom () {
        System.out.println("════════════════════════════════════════════════════════════════════════");
    }
    public static void newLineTop() {
        System.out.println("\n───────────────────────────────────────────────────────────────");
    }
    public static void lineBottom() {
        System.out.println("───────────────────────────────────────────────────────────────");
    }
    public static void timer(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
    public static void timer1500 () {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
    public static void timer1000 () {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
    public static void continueEnter (Scanner scanner, boolean prompt, String message) {
        newLineTop();
        if (prompt) {
            System.out.println(message);
        }
        System.out.println("Press Enter To Continue...");
        lineBottom();
        System.out.println("\n\n");
        scanner.nextLine();
    }
//    public static String autoLineBreakAt100UpTo300 (String input) {
//        StringBuilder returnInput = new StringBuilder();
//        if (input.length() > 100) {
//            while (input.length() > 100) {
//                int breaker = input.lastIndexOf(' ', 100);
//                returnInput.append(input.substring(0, breaker)).append("\n");
//                input = input.substring(breaker + 1);
//            }
//            returnInput.append(input);
//        } if (input.length() < 100) {
//            returnInput = new StringBuilder(input);
//        }
//        return returnInput.toString();
//    }
    public static String autoCap (String input) {
        String [] inputParts = input.toLowerCase().split(" ");
        for (int i = 0; i < inputParts.length; i++) {
            inputParts [i] = inputParts [i].substring(0, 1).toUpperCase() + inputParts [i].substring(1);
        }
        return String.join(" ", inputParts);
    }

    //Booleans //
    public static boolean isEmpty (String input) {
        return input.isEmpty();
    }
    public static boolean isInt (String input, boolean isPositive) {
        try {
            if (input.matches("\\d+")) {
                Integer.parseInt(input);
                if (isPositive) {if (Integer.parseInt(input) < 0) {System.out.println("Input must be a positive number"); return false;}}
                return true;
            } else {
                System.out.println("Incorrect number: " + input);
            }
        } catch (NumberFormatException e) {
            System.out.println("Number not in correct format. Please use whole numbers.");
            return false;
        }
        return false;
    }
    public static boolean isDouble (String input, boolean positive) {
        try {
            if (input.matches("\\d+.\\d+") || input.matches("\\d+")) {
                Double.parseDouble(input);
                if (positive) {if (Double.parseDouble(input) < 0) {System.out.println("Input must be a positive number."); return false;}}
                return true;
            } else {
                System.out.println("Incorrect number: " + input);
            }
        } catch (NumberFormatException e) {
            System.out.println("Number not in correct format. Please use whole numbers.");
            return false;
        }
        return false;
    }


    // System Messages //
    public static void thisFieldCantBeEmpty () {
        System.out.println("This Field Cannot Be Empty!");
    }
    public static void enterPrompt () {
        System.out.print("\n\nEnter:  ");
    }
    public static void message (String message, int newLines) {
        if (newLines == 0) newLines = 2;
        System.out.println(message + "\n".repeat(newLines));
    }
    public static void systemMessage (String message, boolean visualSpacers) {
        if (visualSpacers) Design.titleNewLineTop();
        System.out.println(message);
        if (visualSpacers) Design.titleNewLineTop();
    }

    // Back End //
//    private String activityLogger (Object action, String message) {
//
//    }
//    public static Charset utf () {
//        return StandardCharsets.UTF_8;
//    }
//    public boolean fileBug_1_0(int lineCount) throws IOException {
//        List<String> snailTrail = new ArrayList<>();
//        boolean missing = false;
//        int counter = 0;
//        try (ReversedLinesFileReader buggy = new ReversedLinesFileReader((new File(this.FILE_PATH)),lineCount, utf());
//             BufferedWriter fixIt = new BufferedWriter(new FileWriter(this.FILE_PATH, false))) {
//            String lines = null;
//            while ((lines = buggy.readLine()) != null && counter <= lineCount) {
//                snailTrail.add(lines);
//                counter++;
//                missing = lines.isEmpty() ? true : false;
//            } if (missing) {
//                for (String write : snailTrail) {
//                    fixIt.write(lines);
//                    fixIt.newLine();
//                }
//                lineCount = Math.max(snailTrail.size() - lineCount, 0); // 0 avoids out of bounds error
//            }
//        } catch (IOException e) {
//            System.out.println("Failure at: " + lineCount + "in" + this.FILE_PATH);
//        }
//        return missing;
//    }

    // User Input //
    public static int getIntFromPrompt(Scanner scanner, boolean prompt, String question, boolean isPositive) {
        boolean keepGoing = true;
        String userInput = null;

        while (keepGoing) {
            if (prompt) {
                titleNewLineTop();
                System.out.println(question);
                titleLineBottom();
            }
            enterPrompt();
            userInput = scanner.nextLine().trim().replaceAll("\\s+", " ");
            if (isEmpty(userInput))  {thisFieldCantBeEmpty(); continue;}
            if (!isInt(userInput, isPositive)) {System.out.println("Please use correct number format."); continue;}
            keepGoing = false;
        }
        return Integer.parseInt(userInput);
    }
    public static int getIntWithMinMax(Scanner scanner, boolean prompt, String question, boolean isPositive, int min, int max) {
        boolean keepGoing = true;
        int i = 0;
        while (keepGoing) {
            i = Design.getIntFromPrompt(scanner, prompt, question, isPositive);
            if (min > i || max < i) {
                System.out.println("Please choose a number in range.");
                continue;
            }
            keepGoing = false;
        }
        return i;
    }
    public static double getDoubleFromPrompt (Scanner scanner, boolean prompt, String question, boolean isPositive) {
        boolean keepGoing = true;
        String userInput = null;

        while (keepGoing) {
            if (prompt) {
                titleNewLineTop();
                System.out.println(question);
                titleLineBottom();
            }
            enterPrompt();
            userInput = scanner.nextLine().trim().replaceAll("\\s+", " ");
            if (isEmpty(userInput))  {thisFieldCantBeEmpty(); continue;}
            if (!isDouble(userInput, isPositive)) {System.out.println("Please use correct number format."); continue;}
            keepGoing = false;
        }
        return Double.parseDouble(userInput);
    }
    public static String getPhoneNumString (Scanner scanner, boolean prompt, String promptMessage) {
        while (true) {
            String phoneNum = getNounPrompt(scanner, prompt, promptMessage, false);
            phoneNum = phoneNum.replaceAll("\\s+", "");
            if (phoneNum.matches("\\d{10}")) {
                return "(" + phoneNum.substring(0,3) + ") " + phoneNum.substring(3, 6) + "-" + phoneNum.substring(6,10);
            }
            if (phoneNum.matches("\\(" + "\\d{3}" + "\\)" +  "\\d{3}" + "-" + "\\d{4}$")) {
                return phoneNum.substring(0, 5) + " " + phoneNum.substring(5);
            }
            if (phoneNum.matches("\\(" + "\\d{3}" + "\\)" + "\\d{7}")) {
                return phoneNum.substring(0, 5) + " " + phoneNum.substring(5, 8) + "-" + phoneNum.substring(8);
            }
            if (phoneNum.matches("\\d{3}" + "-" + "\\d{3}" + "-" + "\\d{4}$")) {
                phoneNum = phoneNum.replaceAll("-", "");
                return "(" + phoneNum.substring(0, 3) + ") " + phoneNum.substring(3, 6) + "-" + phoneNum.substring(6);
            }
            System.out.println("Invalid Phone Number");
        }
    }
    public static String getEmailPrompt (Scanner scanner, boolean prompt, String question) {
        String input = null;
        boolean keepGoing = true;
        while (keepGoing) {
            input = Design.getNounPrompt(scanner, prompt, question, false);
            if (!input.matches("(?i)[a-z0-9._%+-]+@[a-z0-9._%+-]+.[a-z]{2,}")) {
                System.out.println("Please enter valid email address");
                continue;
            }
            keepGoing = false;
        } return input;
    }
    public static String getNounPrompt(Scanner scanner, boolean prompt ,String question, boolean autoCapitalize) {
        boolean keepGoing = true;
        String userInput = null;

        while (keepGoing) {
            if (prompt) {
                titleNewLineTop();
                System.out.println(question);
                titleLineBottom();
            }
            enterPrompt();
            userInput = scanner.nextLine().trim().replaceAll("\\s+", " ");
            if (isEmpty(userInput)) {thisFieldCantBeEmpty(); continue;}
            if (autoCapitalize) {userInput = autoCap(userInput);}
            keepGoing = false;
        }
        return userInput;
    }
    public static String getGeneralStringNoPrompt (Scanner scanner) {
        boolean keepGoing = true;
        String userInput = null;

        while (keepGoing) {
            enterPrompt();
            userInput = scanner.nextLine().trim().replaceAll("\\s+", " ");
            if (isEmpty(userInput)) {thisFieldCantBeEmpty(); continue;}
            keepGoing = false;

        }
        return userInput;
    }
    public static String getDateTimeStamp(boolean date, boolean time) {
        LocalDateTime all = LocalDateTime.now();
        String output = null;
        if (date && time) {
            return all.format(DateTimeFormatter.ofPattern("dd-MM-yyyy__HH:mm:ss"));
        } if (date) {
            return all.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } if (time) {
            return all.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        }
        return null;
    }
//    public static LinkedHashMap<String, Object> confirmInputs (Scanner scanner, LinkedHashMap<String, Object> actionInput, boolean posInt, boolean posDouble, boolean autoCap) {
//        // Auto Generated list of inputs for user to confirm with availability to fix upon return. Works with "this.variables".
//        // Make a Linked Hashmap of {"User visible Action Text. EX: CLIENT NAME", variable connected to this input}.
//        boolean keepGoing = true;
//        int reset = 0;
//        int i = reset;
//        while (keepGoing) {
//            i = reset;
//            Design.titleNewLineTop();
//            Design.message("Is this info correct? Yes or no? (Y) or (N).", 0);
//            actionInput.entrySet().stream()
//                    .forEach(AI -> System.out.println
//                            (Design.autoCap(AI.getKey().replace("!", "")) + " Entered: " + (AI.getValue())));
//            Design.titleLineBottom();
//
//            if (Design.getYesNo(scanner, false, "")) {
//                Design.systemMessage("Thank you for confirming", true);
//                return actionInput;
//            } else {
//                Design.titleNewLineTop();
//                Design.message("Select which part to change. If no number is present," +
//                        "this value is not directly editable.", 2);
//                for (Map.Entry<String, Object> AI : actionInput.entrySet()) {
//                    if (AI.getKey().contains("!")) {
//                        System.out.println(Design.autoCap(AI.getKey().replace("!", "")) + " Entered: " + (AI.getValue()));
//                    } if (!AI.getKey().contains("!")) {
//                        i++;
//                        System.out.println(Design.autoCap("(" + (i) + ") " + AI.getKey()) + " Entered: " + (AI.getValue()));
//                    }
//                } i = reset;
//                Design.titleLineBottom();
//                int fixNum = Design.getIntFromPrompt(scanner, false, "", true);
//
//                boolean found = false;
//                String type = null;
//                for (Map.Entry<String, Object> AI : actionInput.entrySet()) {
//                    if (AI.getKey().contains("!")) continue;
//                    i++;
//                    if (i == fixNum && !AI.getKey().contains("!")) {
//                        if (AI.getValue() instanceof Integer) {
//                            actionInput.put(AI.getKey(), Design.getIntFromPrompt(scanner, true, "Please enter " + Design.autoCap(AI.getKey()) + ".", posInt));
//                        } if (AI.getValue() instanceof Double) {
//                            actionInput.put(AI.getKey(), Design.getDoubleFromPrompt(scanner, true, "Please enter " + Design.autoCap(AI.getKey()) + ".", posDouble));
//                        } if (AI.getValue() instanceof String) {
//                            actionInput.put(AI.getKey(), Design.getNounPrompt(scanner, true, "Please enter " + Design.autoCap(AI.getKey()) + ".", autoCap));
//                        }
//                    }
//                }
//            }
//        }
//        return null;
//    }
    public static boolean getYesNo (Scanner scanner, boolean prompt, String message) {
        String input;
        while (true) {
            if (prompt) {
                Design.titleNewLineTop();
                if (!message.isEmpty()) System.out.println(message);
                System.out.println("Yes or No? (Y) ? (N)");
                Design.titleLineBottom();
            }
            input = Design.getNounPrompt(scanner, false, "", false).toUpperCase();
            switch (input.charAt(0)) {
                case 'Y' -> {return true;}
                case 'N' -> {return false;}
                default -> System.out.println("Please enter (Y) for yes (N) for no.");
            }
        }
    }
}

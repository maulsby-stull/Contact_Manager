package data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String response;
        List<String> contacts = new ArrayList<>();
        Path contactPath = Paths.get("src/data", "contacts.txt");

        System.out.println("    ______   ______   .__   __. .___________.    ___       ______ .___________.    _______.\n" +
                "   /      | /  __  \\  |  \\ |  | |           |   /   \\     /      ||           |   /       |\n" +
                "  |  ,----'|  |  |  | |   \\|  | `---|  |----`  /  ^  \\   |  ,----'`---|  |----`  |   (----`\n" +
                "  |  |     |  |  |  | |  . `  |     |  |      /  /_\\  \\  |  |         |  |        \\   \\    \n" +
                "  |  `----.|  `--'  | |  |\\   |     |  |     /  _____  \\ |  `----.    |  |    .----)   |   \n" +
                "   \\______| \\______/  |__| \\__|     |__|    /__/     \\__\\ \\______|    |__|    |_______/    \n" +
                "2                                                                                           ");


        contacts = Files.readAllLines(contactPath);
        System.out.println("Name                 |              Phone number\n" +
                "------------------------------------------------");
        for(String UserInfo : contacts){
            System.out.println(UserInfo);
        }
        do {
            System.out.println(
                    "\n1. View contacts.\n" +
                            "2. Add a new contact.\n" +
                            "3. Search a contact by name.\n" +
                            "4. Delete an existing contact.\n" +
                            "5. Update a contact's info.\n" +
                            "6. Exit.\n" +
                            "Enter an option (1, 2, 3, 4, 5 or 6):");
            String option = scanner.nextLine();
            switch(option){
                case "1":

                    contacts = Files.readAllLines(contactPath);
                    System.out.println("Name                 |              Phone number\n" +
                            "------------------------------------------------");
                    for(String UserInfo : contacts){
                        System.out.println(UserInfo);
                    }

                    break;
                case "2":
                    String num = "";
                    System.out.println("What name would you like add?");
                    String name = scanner.nextLine();
                    System.out.println("What is their phone number?");
                    String phoneNumber = scanner.nextLine();
                    if(phoneNumber.length() == 10){
                        num = "("+phoneNumber.substring(0,3)+")-"+phoneNumber.substring(3,6)+"-"+phoneNumber.substring(6,phoneNumber.length());
                    }else if(phoneNumber.length() == 7){
                        num = phoneNumber.substring(0,3)+"-"+phoneNumber.substring(3,phoneNumber.length());
                    }

//                    String user = (name+" | "+num);
                    String user = String.format("%-20s | %25s",name,num);
                    contacts.add(user);
                    break;
                case "3":
                    ArrayList<String> info = new ArrayList<>();
                    System.out.println("Who would you like to search for?");
                    String output = scanner.nextLine();
                    for(String UserInfo : contacts){
                        if(UserInfo.toLowerCase().contains(output.toLowerCase())){
                            info.add(UserInfo);
                        }
                    }
                    if(info.size()>0) {
                        System.out.println("Here are all the users with the name of " + output+":\n");
                        System.out.println("Name                    |              Phone number\n" +
                                "---------------------------------------------------");
                        int i = 1;
                        for (String users : info) {
                            System.out.println("" + i +": "+ users);
                            i++;
                        }
                    }
                    else{
                        System.out.println("Impossible, Perhaps the archives are incomplete?!");
                    }

                    break;
                case "4":
                    ArrayList<String> toDelete = new ArrayList<>();
                    System.out.println("Who would you like to delete?");
                    String delete = scanner.nextLine();

                    for(String UserInfo : contacts){
                        if(UserInfo.toLowerCase().contains(delete.toLowerCase())){
                            toDelete.add(UserInfo);
                        }
                    }
                    if(toDelete.size()>1) {
                        System.out.println("There are multiple users with the name of " + delete + ".\n" +
                                "Who would you like to delete out of the list?(Positive Number)\n");
                        String yesNo;
                        do {
                            System.out.println("Name                    |              Phone number\n" +
                                    "---------------------------------------------------");
                            int i = 1;
                            for (String users : toDelete) {
                                System.out.println("" + i + ": " + users);
                                i++;
                            }
                            int select = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Are you sure you want to delete this user(y/n)?\n" + toDelete.get(select - 1));
                            yesNo = scanner.nextLine();
                            if (yesNo.equalsIgnoreCase("y")) {
                                contacts.remove(toDelete.get(select - 1));
                            }
                        }while(!yesNo.equalsIgnoreCase("y"));
                    }else{
                        contacts.remove(toDelete.get(0));
                    }
                    break;
                case "5":
                    ArrayList<String> edit = new ArrayList<>();
                    System.out.println("Who would you like to edit?");
                    String username = scanner.nextLine();
                    for(String UserInfo : contacts){
                        if(UserInfo.toLowerCase().contains(username.toLowerCase())){
                            edit.add(UserInfo);
                        }
                    }
                    String User="";
                    if(edit.size()>1) {
                        System.out.println("Which user would you like to edit?\n");
                        System.out.println("Name                    |              Phone number\n" +
                                "---------------------------------------------------");
                        int i = 1;
                        for (String person : edit) {
                            System.out.println("" + i +": "+ person);
                            i++;
                        }
                        int select = scanner.nextInt();
                        scanner.nextLine();
                        User = edit.get(select -1);
                    }else if(edit.size()==1) {
                        User =edit.get(0);
                    }else{
                        System.out.println("Impossible, Perhaps the archives are incomplete?!");
                    }
                    String phonenum = "";
                    System.out.println("What name would you like change the existing contact to?");
                    String Name = scanner.nextLine();
                    System.out.println("What is their phone number?");
                    String phonenumber = scanner.nextLine();
                    if(phonenumber.length() == 10){
                        phonenum = "("+phonenumber.substring(0,3)+")-"+phonenumber.substring(3,6)+"-"+phonenumber.substring(6,phonenumber.length());
                    }else if(phonenumber.length() == 7){
                        phonenum = phonenumber.substring(0,3)+"-"+phonenumber.substring(3,phonenumber.length());
                    }
                    String useredit = String.format("%-20s | %25s",Name,phonenum);
                    contacts.set(contacts.indexOf(User), useredit);
                    break;
                case "6":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Thats not an options!");
                    break;
            }

            System.out.println("\nWould you like to look at more contact info?(y/n)");
            response = scanner.nextLine();
            Files.write(contactPath, contacts);
        }while(response.equals("y"));
    }
}

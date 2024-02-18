
import java.util.*;

class Server {
    // creating arraylist to store details entered by customers during login

    public static ArrayList<String> Name = new ArrayList<>();
    public static ArrayList<String> Email_id = new ArrayList<>();
    public static ArrayList<String> Aadhar_id = new ArrayList<>();
    public static ArrayList<String> password = new ArrayList<>();
    public static ArrayList<String> MobileNumber = new ArrayList<>();
    public static ArrayList<Integer> Intial_amt = new ArrayList<>();
    public static ArrayList<String> Final_Name = new ArrayList<>();
    public static ArrayList<String> Final_Email_id = new ArrayList<>();
    public static ArrayList<String> Final_Aadhar_id = new ArrayList<>();
    public static ArrayList<String> Final_password = new ArrayList<>();
    public static ArrayList<String> Final_MobileNumber = new ArrayList<>();
    public static ArrayList<Integer> Final_Intial_amt = new ArrayList<>();
    public static ArrayList<String> Customer_ID = new ArrayList<>();
    public static int Customer_idNo = 1;

    // creating a hashmap to manage transactions

    public static HashMap<String, ArrayList<String>> transaction_id = new HashMap<>();

    public static void Welcome() {
        // next line is used to get input containg space within them
        System.out.println("please enter your name,email id,aadhar id ,mobilenumber,password,initial amt \n----- ");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        String email = sc.next();
        String aadhar = sc.next();
        String number = sc.next();
        int amount = sc.nextInt();
        System.out.println("please note the password should not  contain your name or your phone number \n----- ");
        String pass = sc.next();
        boolean check = VerifyPassword(pass, name, number);
        while (check != true) {
            pass = sc.next();
            check = VerifyPassword(pass, name, number);
        }
        Name.add(name);
        Email_id.add(email);
        Aadhar_id.add(aadhar);
        password.add(pass);
        MobileNumber.add(number);
        Intial_amt.add(amount);
        sc.close();
    }

    // this function is used to check whether the password meets the required
    // conditions

    public static boolean VerifyPassword(String s, String name, String number) {
        boolean ans = false;
        boolean a = s.equals(name);
        boolean b = s.equals(number);
        boolean c = name.contains(s);
        if (a != true && b != true && c != true) {
            if (s.length() >= 8) {
                int di = 0, lt = 0, wp = 0, sp = 0;
                for (int i = 0; i < s.length(); ++i) {
                    char ch = s.charAt(i);
                    if (Character.isDigit(ch)) {
                        di++;
                    } else if (Character.isLetter(ch)) {
                        lt++;
                    } else if (ch == ' ') {
                        wp++;
                    } else {
                        sp++;
                    }
                }
                if (di > 0 && lt > 0 && sp > 0 && wp == 0) {
                    ans = true;
                }
            }
        }
        return ans;
    }

    // Check_Status function is used to check the status of application by customer

    public static void Check_Status() {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        if (Final_Name.contains(s)) {
            System.out.println("Congratulations you have created your account in our bank");
            int pos = Final_Name.indexOf(s);
            System.out.println(" please note Your customer id : " + Customer_ID.get(pos));
        } else {
            System.out.println(
                    "Sorry you did meet our required needs to create a account in our bank \nNOTE: IF YOU APPLIED WITHIN LAST DAYS PLEASE TRY AGAIN AFTER SOMETIME \n-----");
        }
        sc.close();
    }

    // this is function is used by employee to do his work

    public static void Employee_Work() {
        Scanner sc = new Scanner(System.in);
        System.out.println("WELCOME press 1 to do status wrok or 2 remaining work");
        int choice = sc.nextInt();

        if (choice == 1) // using this function user can manually accept and reject customer applications
        {
            for (int i = 0; i < Name.size(); ++i) {
                System.out.println("Details of the customer \n-----");
                System.out.println(
                        Name.get(i) + "\n" + Email_id.get(i) + "\n" + Aadhar_id.get(i) + "\n" + MobileNumber.get(i));
                System.out.println("Enter whether to accept or reject");
                int choice1 = sc.nextInt();
                if (choice1 == 1) {
                    Final_Name.add(Name.get(i));
                    Final_Email_id.add(Email_id.get(i));
                    Final_Aadhar_id.add(Aadhar_id.get(i));
                    Final_MobileNumber.add(MobileNumber.get(i));
                    Final_Intial_amt.add(Intial_amt.get(i));
                    Final_password.add(password.get(i));
                    String ID = "938420" + Integer.toString(Customer_idNo);
                    Customer_ID.add(ID);
                    Customer_idNo++;
                    transaction_id.put(Customer_ID.get(i), new ArrayList<String>());
                    String to_add = "+" + Final_Intial_amt.get(i);
                    transaction_id.get(Customer_ID.get(i)).add(to_add);
                }
            }
            Name.clear();
            Email_id.clear();
            Aadhar_id.clear();
            MobileNumber.clear();
            password.clear();
            Intial_amt.clear();
        }
        sc.close();
    }

    // User_Login function is used for user accessing purpose

    public static void User_Login() {

        // verifying for correct id and password for login in

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your customer id : ");
        String customer_id = sc.nextLine();
        if (Customer_ID.contains(customer_id)) {
            int pos = Customer_ID.indexOf(customer_id);
            System.out.println("Enter your password : ");
            String login_pass = sc.next();
            boolean check = login_pass.equals(Final_password.get(pos));
            while (!check) {
                System.out.println("Enter valid password : ");
                login_pass = sc.next();
                check = login_pass.equals(Final_password.get(pos));
            }
            System.out.println(
                    "Press 1 to change password \npress 2 to see transactions \npress 3 to deposit or credit  \npress 4 to see your informations ");
            int choice = sc.nextInt();
            if (choice == 1) {
                System.out.println("Enter new password : ");
                String pass = sc.next();
                check = VerifyPassword(pass, Final_Name.get(pos), Final_MobileNumber.get(pos));
                while (!check) {
                    System.out.println("Enter valid password : ");
                    pass = sc.next();
                    check = VerifyPassword(pass, Final_Name.get(pos), Final_MobileNumber.get(pos));
                }
                Final_password.set(pos, pass);
                System.out.println("Your password is updated ");
            } else if (choice == 2) {
                System.out.println(transaction_id.get(customer_id));
            } else if (choice == 3) {
                System.out.println("Please press 1 to credit \npress 2 to deposit ");
                int choice1 = sc.nextInt();
                if (choice1 == 1) {
                    System.out.println("enter the amount to credit ");
                    int credit_amt = sc.nextInt();
                    String to_add = "+" + Integer.toString(credit_amt);
                    transaction_id.get(customer_id).add(to_add);
                    int final_amt = credit_amt + Final_Intial_amt.get(pos);
                    Final_Intial_amt.set(pos, final_amt);
                    System.out.println("Remaining Balance : " + Final_Intial_amt.get(pos));
                } else if (choice1 == 2) {
                    System.out.println("Enter the amount to withdraw : ");
                    int deposit_amt = sc.nextInt();
                    if (Final_Intial_amt.get(pos) - deposit_amt >= 0) {
                        String to_minus = "-" + Integer.toString(deposit_amt);
                        transaction_id.get(customer_id).add(to_minus);
                        int final_amt = Final_Intial_amt.get(pos) - deposit_amt;
                        Final_Intial_amt.set(pos, final_amt);
                        System.out.println("Remaining Balance : " + Final_Intial_amt.get(pos));
                    } else {
                        System.out.println("Insufficent balance ");
                    }
                } else {
                    System.out.println("Enter valid option");
                }
            } else if (choice == 4) {
                System.out.println("Name : " + Final_Name.get(pos));
                System.out.println("Email id : " + Final_Email_id.get(pos));
                System.out.println("Aadhar : " + Final_Aadhar_id.get(pos));
                System.out.println("Mobile Number : " + Final_MobileNumber.get(pos));
                System.out.println("Customer id : " + Customer_ID.get(pos));
                System.out.println("password : " + Final_password.get(pos));
                System.out.println("Balance : " + Final_Intial_amt.get(pos));
            } else {
                System.out.println("Choose valid option :");
            }
        } else {
            System.out.println("Sorry your application is rejected try contacting bank for further details");
        }
        sc.close();
    }
}

public class bankingconsole extends Server {
    public static void main(String[] args) {
        Scanner sc1 = new Scanner(System.in);

        // asking their choice of work with bank

        System.out.println(
                "welcome to the bank \n1.if your here to create a account press 1 \n2.if your here to view existing account details press 2 \n3.press 3 for employees \n----- ");

        boolean satisified = false;
        while (!satisified) {
            System.out.println("Enter your choice : ");
            int choice = sc1.nextInt();
            if (choice == 1) {
                System.out.println(
                        "THANKYOU FOR CHOOSING US \npress 1 to create application \npress 2 to check status \n-----");
                int choice1 = sc1.nextInt();
                if (choice1 == 1) {
                    Welcome();
                } else if (choice1 == 2) {
                    Check_Status();
                } else {
                    System.out.println("Enter valid option");
                }
            } else if (choice == 2) {
                User_Login();
            } else if (choice == 3) {
                System.out.println("Welcome please enter your employee no : ");
                int employee_number = sc1.nextInt(), count = 0;
                for (int i = 100; i <= 200; ++i) {
                    if (i == employee_number) {
                        count++;
                        Employee_Work();
                        break;
                    }
                }
                if (count == 0) {
                    System.out.println("You are not an employee");
                }
            } else if (choice == 4) {
                satisified = true;
                System.out.println("----- \nTHANKYOU FOR VISITING US \n-----");
            } else {
                System.out.println("Enter valid option");
            }
        }
        sc1.close();
    }
}
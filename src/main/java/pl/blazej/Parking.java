package pl.blazej;
import pl.blazej.dao.DaoImplements;
import java.util.Scanner;

public class Parking {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String password = "Ala";
        String yourPassword;

        int menuOption=0;
        int ifAdmin;

        int [] parkingPlaces= new int[200];
        String []adminNames={"admin1","admin2"};
        String []userNames={"user1","user2","user3","user4"};
        String logName;

        DaoImplements daoImplements = new DaoImplements();

        do{
            ifAdmin=0;
            System.out.println("Wpisz swoj nick, ew 0 gdy chcesz wyjsc");
            logName=scanner.nextLine();
            for (String adminName : adminNames) {
                if(logName.equals(adminName)){
                    ifAdmin=1;
                }
            }
            if(ifAdmin != 1) {
                for (String userName : userNames) {
                    if (userName.equals(logName)) {
                        ifAdmin = 2;
                    }
                }
            }
            if(ifAdmin == 0){
                System.out.println("Bledny nick, wychodze z programu");
                break;
            }

            if(ifAdmin == 1) {

                System.out.println("Podaj haslo admina: ");
                yourPassword = scanner.nextLine();
                if(!(yourPassword.equals(password))){
                    System.out.println("Podales zle haslo");
                    continue;
                }
                do {
                    System.out.println("Jestes adminem");
                    System.out.println("Podglad zajetych miejsc parkingowych - 1");
                    System.out.println("Wyrzucenie z miejsca parkingowego - 2");
                    System.out.println("Zwolnienie wszystkich miejsc parkingowych -3");
                    System.out.println("Jesli chcesz wyjsc z programu -0");

                    String menuOptionString=scanner.nextLine();
                    try {
                         menuOption = Integer.parseInt((menuOptionString));
                    }catch(NumberFormatException e){
                        System.out.println("Zle wprowadzona opcja nastepuje wylogowywanie ");
                        break;
                    }
                    switch (menuOption) {
                        case 1:
                            daoImplements.showAll();
                            System.out.println("Zajete miejsca: "+ daoImplements.showAll());
                            break;

                        case 2:
                            System.out.println("Zajete miejsca: "+ daoImplements.showAll());
                            System.out.println("Podaj miejsce ktore chcesz zwolnic");
                            int place;
                            try{
                                place = Integer.parseInt(scanner.nextLine());
                            }catch(NumberFormatException e){
                                System.out.println("Zły format liczby");
                                break;
                            }

                               if( place<0 || place>parkingPlaces.length){
                                   System.out.println("Nie ma takiego miejsca parkingowego");
                                   break;
                               }
                            for(Integer user: daoImplements.showAll()){
                                   if(user == place ){
                                       daoImplements.removeCar(place);
                                       System.out.println("Wyrzuciles samochod z miejsca numer: "+ place);
                                   }
                            }
                            System.out.println("Miejsce które wybrałes jest puste");
                            break;

                        case 3:
                            System.out.println("Zwalniam parking");
                            daoImplements.removeAllCars();
                            break;

                        case 0:
                            System.out.println("Wychodze do wyboru uzytkownika");
                            break;
                        default:
                            System.out.println("Wprowadzono zla opcje");
                            break;
                    }
                }while(menuOption!=0);

            }else if(ifAdmin == 2){
                do {
                    System.out.println("Jestes uzytkownikiem");
                    System.out.println("Parkowanie pojazdu -1");
                    System.out.println("Odjazd parkingu -2");
                    System.out.println("Zmiana miejsca parkingowego -3");
                    System.out.println("Wyjscie -0");
                    try {
                        menuOption = Integer.parseInt(scanner.nextLine());
                    }catch(NumberFormatException e){
                        System.out.println("Zly format nastepuje wylogowywanie ");
                        break;
                    }
                    switch(menuOption){
                        case 1:

                            UserParking car;
                            System.out.println("Na którym miejscu zaparkowac?");
                            int whichPlace;
                            try{
                                whichPlace = Integer.parseInt(scanner.nextLine());
                            }catch(NumberFormatException e){
                                System.out.println("Zle wprowadzona opcja nastepuje wylogowywanie ");
                                break;
                            }

                            if(whichPlace<0 ||whichPlace>parkingPlaces.length){
                                System.out.println("Nie ma takiego miejsca parkingowego");
                                break;
                            }

                            boolean isTheSame = false;
                            for(Integer users: daoImplements.showAll()){
                                isTheSame = true;
                                if(whichPlace == users ){
                                    System.out.println("Miejsce na ktorym chcesz zaparkowac jest zajete");
                                    isTheSame = false;
                                    break;
                                }
                                }
                            if (isTheSame) {
                                car = new UserParking("0",whichPlace,false);
                                daoImplements.addCar(car);
                                System.out.println("Zaparkowales na miejscu numer: " +whichPlace);
                            }
                            break;

                        case 2:
                            System.out.print("Podaj miejsce z ktorego chcesz odjechac");
                            int userPlaceToRemove;
                            try{
                                userPlaceToRemove=Integer.parseInt(scanner.nextLine());
                            }catch(NumberFormatException e){
                                System.out.println("Zle wprowadzona opcja nastepuje wylogowywanie ");
                                break;
                            }

                            if(userPlaceToRemove< 0||userPlaceToRemove>= parkingPlaces.length){
                                System.out.println("nie ma takiego miejsca");
                                break;
                            }

                            for(Integer users: daoImplements.showAll()){
                                if(userPlaceToRemove == users){
                                    daoImplements.removeCar(userPlaceToRemove);
                                    System.out.println("Odjechales z " +userPlaceToRemove+" miejsca parkingowego");
                                    break;
                                }
                            }


                            break;
                        case 3:
                            System.out.println("Podaj nowe miejsce parkingowe");
                            System.out.println("Podaj gdzie zaparkowales samochod");
                            int newPlace;
                            int oldPlace;

                            try {
                                newPlace = Integer.parseInt(scanner.nextLine());
                                oldPlace= Integer.parseInt(scanner.nextLine());
                            }catch(NumberFormatException e){
                                System.out.println("Zly format liczby ");
                                break;
                            }

                            if(oldPlace>=parkingPlaces.length || newPlace>=parkingPlaces.length || oldPlace<0 || newPlace<0) {
                                System.out.println("Nie ma takiego miejsca parkingowego");
                                break;
                            }

                            int newPlaceCar=0;
                            int oldPlaceCar=0;
                            for(Integer places: daoImplements.showAll()){//todo przeanalizowac
                                if(newPlace != places){
                                    newPlaceCar = places;
                                }
                                if(oldPlace == places){
                                    oldPlaceCar = places;
                                }

                            }

                            if(newPlaceCar!=0 && oldPlaceCar!=0) {
                                daoImplements.changeCarsInParking(newPlace, oldPlace);
                                System.out.println("Samochod został przestawiony na nowe miejsce");
                                break;
                            }
                            System.out.println("Cos poszlo nie tak");


                        case 0:
                            System.out.println("Do widzenia");
                            break;
                        default:
                            System.out.println("Wprowadzono zla opcje");
                            break;
                    }
                }while(menuOption!=0);
            }
        }while(ifAdmin != 0);
    }
}
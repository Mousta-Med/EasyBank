import com.EasyBank.controller.ClientController;
import com.EasyBank.controller.CompteCourantController;
import com.EasyBank.controller.EmployeController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmployeController employeController = new EmployeController();
        ClientController clientController = new ClientController();

        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println(
                    """
                            ++++++++++++++++++++   EasyBank   +++++++++++++++++++++
                            + 1.Ajouter Un Employe                                +
                            + 2.Supprimer Un Employe                              +
                            + 3.Afficher Employe par matricule                    +
                            + 4.Ajouter Un Client                                 +
                            + 5.Supprimer Un client                               +
                            + 6.Aficher Un Client par code                        +
                            + 7.Ajouter Un Compte                               +
                            + 8.Emprunter Un Livre                                +
                            + 10.Afficher Les Members                             +
                            + 11.Afficher Les Livres Emprunter                    +
                            + 12.Retourner Un Livre Emprunter                     +
                            + 13.Aficher Statistique                              +
                            + 14.Exporter Statistique                             +
                            + 0.Sortir                                            +
                            +++++++++++++++++++++++++++++++++++++++++++++++++++++++
                            Entrer Votre Choix:\s""");
            String input = scanner.next();
            switch (input) {
                case "1":
                    employeController.ajouterEmploye();
                    break;
                case "2":
                    employeController.deleteEmploye();
                    break;
                case "3":
                    employeController.recherchEmploye();
                    break;
                case "4":
                    clientController.ajouterClient();
                    break;
                case "5":
                    clientController.supprimerClient();
                    break;
                case "6":
                    clientController.chercherClient();
                    break;
                case "7":
                    CompteCourantController.ajouterCompte();
                    break;
                case "8":
                    CompteCourantController.supprimerCompte();
                    break;
                case "9":
                    break;
                case "10":
                    break;
                case "11":
                    break;
                case "12":
                    break;
                case "13":
                    break;
                case "14":
                    break;
                case "0":
                    System.out.println("Merci...");
                    System.exit(0);
                default:
                    System.out.println("Votre Choix Est Invalide");
            }

        } while (true);
    }
}
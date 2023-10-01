import com.EasyBank.controller.*;

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
                            + 7.Ajouter Un CompteCourant                          +
                            + 8.Supprimer Un compteCourant                        +
                            + 9.Afficher Un compteCourant par client             +
                            + 10.Ajouter Un compteEpargne                          +
                            + 11.Supprimer Un compteEpargne                        +
                            + 12.Afficher Un compteEpargne par client
                            + 13.ajouter operation                                +
                            + 14.supprimer operation                              +
                            + 15.chercher operation                               +
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
                    CompteCourantController.chercherCompte();
                    break;
                case "10":
                    CompteEpargneController.ajouterCompte();
                    break;
                case "11":
                    CompteEpargneController.supprimerCompte();
                    break;
                case "12":
                    CompteEpargneController.chercherCompte();
                    break;
                case "13":
                    OperationController.ajouterOperation();
                    break;
                case "14":
                    OperationController.supprimerOperation();
                    break;
                case "15":
                    OperationController.chercherOperation();
                    break;
                case "16":
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
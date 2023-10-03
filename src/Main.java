import com.EasyBank.service.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmployeService employeService = new EmployeService();
        ClientService clientService = new ClientService();

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
                    + 9.Afficher Un compteCourant par client              +
                    + 10.Ajouter Un compteEpargne                         +
                    + 11.Supprimer Un compteEpargne                       +
                    + 12.Afficher Un compteEpargne par client             +
                    + 13.Ajouter une opération                            +
                    + 14.Supprimer une opération                          +
                    + 15.Chercher une opération                           +
                    + 16.Exporter Statistiques                            +
                    + 17.Ajouter Mission                                  +
                    + 18.Supprimer Mission                                +
                    + 19.Afficher toutes les opérations                   +
                    + 20.Afficher tous les employés                       +
                    + 21.Rechercher employé par attribut                  +
                    + 22.Afficher tous les clients                        +
                    + 23.Chercher client par attribut                     +
                    + 24.Mise à jour de l'état d'un compte                +
                    + 25.Afficher les comptes par statut                  +
                    + 26.Afficher les comptes par date                    +
                    + 27.Afficher toutes les missions                     +
                    + 28.Ajouter une affectation                          +
                    + 29.Supprimer une affectation                        +
                    + 30.Mise à jour de l'employé                         +
                    + 31.Mise à jour du client                            +
                    + 32.Mise à jour du compte courant                    +
                    + 33.Afficher le compte par opération                 +
                    + 34.Afficher les affectations employé                +
                    + 0.Sortir                                            +
                    +++++++++++++++++++++++++++++++++++++++++++++++++++++++
                    Entrer Votre Choix:\s""");
            String input = scanner.next();
            switch (input) {
                case "1":
                    employeService.ajouterEmploye();
                    break;
                case "2":
                    employeService.deleteEmploye();
                    break;
                case "3":
                    employeService.recherchEmploye();
                    break;
                case "4":
                    clientService.ajouterClient();
                    break;
                case "5":
                    clientService.supprimerClient();
                    break;
                case "6":
                    clientService.chercherClient();
                    break;
                case "7":
                    CompteCourantService.ajouterCompte();
                    break;
                case "8":
                    CompteCourantService.supprimerCompte();
                    break;
                case "9":
                    CompteCourantService.chercherCompte();
                    break;
                case "10":
                    CompteEpargneService.ajouterCompte();
                    break;
                case "11":
                    CompteEpargneService.supprimerCompte();
                    break;
                case "12":
                    CompteEpargneService.chercherCompte();
                    break;
                case "13":
                    OperationService.ajouterOperation();
                    break;
                case "14":
                    OperationService.supprimerOperation();
                    break;
                case "15":
                    OperationService.chercherOperation();
                    break;
                case "16":
                    MissionService.ajouterMission();
                    break;
                case "17":
                    MissionService.supprimerMission();
                    break;
                case "18":
                    OperationService.afficherOperations();
                    break;
                case "19":
                    employeService.afficherEmlpoyes();
                    break;
                case "20":
                    employeService.recherchEmployeParAttribut();
                    break;
                case "21":
                    clientService.afficherClients();
                    break;
                case "22":
                    clientService.chercherClientParAttribute();
                    break;
                case "23":
                    CompteCourantService.updateCompteEtat();
                    break;
                case "24":
                    CompteCourantService.afficheComptesParStatut();
                    break;
                case "25":
                    CompteCourantService.afficheComptesParDate();
                    break;
                case "26":
                    MissionService.afficherMissions();
                    break;
                case "27":
                    AffectationService.ajouterAffectation();
                    break;
                case "28":
                    AffectationService.supprimerAffectation();
                    break;
                case "29":
                    employeService.miseajourEmploye();
                    break;
                case "30":
                    clientService.miseajourClient();
                    break;
                case "31":
                    CompteCourantService.miseajourCompte();
                    break;
                case "32":
                    CompteCourantService.afficherCompteParOperation();
                    break;
                case "33":
                    AffectationService.afficherAffectationEmploye();
                    break;
                case "34":
                    AffectationService.afficherAffectation();
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
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class PersonneC {
    private final String nom;
    private final String prenom;
    private final int age;

    public PersonneC(String prenom, String nom, int age) {
        this.prenom = prenom;
        this.nom = nom;
        this.age = age;
    }

    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public int getAge() { return age; }

    @Override
    public String toString() {
        return prenom + " " + nom + " (" + age + ")";
    }
}

public class ComparateurCompose {
    public static void main(String[] args) {
        List<PersonneC> personnes = Arrays.asList(
                new PersonneC("Jean", "Dupont", 30),
                new PersonneC("Marie", "Martin", 25),
                new PersonneC("Pierre", "Dupont", 40),
                new PersonneC("Sophie", "Martin", 35),
                new PersonneC("Paul", "Dupont", 20)
        );

        Comparator<PersonneC> comparateur = Comparator
                .comparing(PersonneC::getNom)
                .thenComparing(PersonneC::getPrenom)
                .thenComparingInt(PersonneC::getAge);

        System.out.println("Liste triée:");
        personnes.stream().sorted(comparateur).forEach(System.out::println);

        Comparator<PersonneC> comparateurVariante = Comparator
                .comparing(PersonneC::getNom)
                .thenComparing(PersonneC::getAge, Comparator.reverseOrder());

        System.out.println("\nListe triée (variante):");
        personnes.stream().sorted(comparateurVariante).forEach(System.out::println);
    }
}

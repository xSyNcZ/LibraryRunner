import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LibraryRunner {
	private Map<String, Library> libraries;
	private boolean on;
	private Scanner sc;

	private final static String FANTASY = "FANTASY";
	private final static String SPORT = "SPORT";

	public LibraryRunner() {
		libraries = new HashMap<>();
		sc = new Scanner(System.in);

		libraries.put(FANTASY, new Library("FANTASY"));
		libraries.put(SPORT, new Library("SPORT"));

		libraries.get(FANTASY).addBook(new Book("Andrzej Pilipiuk", "Przetaina", "Fabrka Słów", 400));
		libraries.get(FANTASY).addBook(new Book("Anthony Ryan", "Zew Wilka", "MAG", 480));
		libraries.get(FANTASY).addBook(new Book("Joe Abercrombie", "Mądrość tłumu", "MAG", 752));

		libraries.get(SPORT).addBook(new Book("Ivette Żółtowska-Darska", "Ronaldinho. Czarodziej piłki nożnej", "SQN", 174));
		libraries.get(SPORT).addBook(new Book("Richard Williams", "Wieczny Ayrton Senna", "SQN", 296));
		libraries.get(SPORT).addBook(new Book("Simon Kuper","Barca. Powstanie i upadek klubu, który kształtował nowoczesną piłkę nożną", "SQN", 510));
	}

	public void turnOn() {
		on = true;
		while (on) {
			displayMenu();
			int opcja = getOption();
			chooseOption(opcja);
		}
	}

	public void displayMenu() {
		System.out.println("Wybierz opcję:");
		System.out.println("	(0) Wyświetl bibliotekę");
		System.out.println("	(1) Dodaj bibliotekę");
		System.out.println("	(2) Zduplikuj książkę w bibliotece");
		System.out.println("	(3) Usuń książkę w bibliotece");
		System.out.println("	(4) Zakończ działanie programu");
	}

	public int getOption() {
		int option = sc.nextInt();
		return option;
	}

	public void chooseOption(int option) {
		switch (option) {
		case 0:
			viewLibrary();
			break;

		case 1:
			addLibrary();
			break;

		case 2:
			multiplyBook();
			break;
		case 3:
			deleteBook();
			break;
		case 4:
			System.exit(0);
			break;

		default:
			System.err.println("Nie znaleziono takiej opcji");
		}
	}

	public void viewLibrary() {
		System.out.println("Dostępne biblioteki: ");
		displayLibraries();
		System.out.println("Którą bibliotekę chcesz wyświetlić: ");
		sc.nextLine();
		String name = sc.nextLine();
		if (libraries.containsKey(name.toUpperCase()) != true) {
			System.out.println("Nie ma takiej biblioteki");
		} else {
			Library library = libraries.get(name.toUpperCase());
			library.showLibrary();
		}
	}

	public void addLibrary() {
		System.out.println("Podaj nazwę biblioteki, którą chcesz dodać: ");
		sc.nextLine();
		String name = sc.nextLine();
		Library library = new Library(name.toUpperCase());
		boolean nameExists = libraries.containsKey(name.toUpperCase());
		if (nameExists == true) {
			System.out.println("Taka biblioteka już istnieje");
		} else {
			libraries.put(name.toUpperCase(), library);
			System.out.println("Dodano bibliotekę");
		}
	}

	public void multiplyBook() {
		System.out.println("Dostępne biblioteki: ");
		displayLibraries();
		System.out.println("Z której biblioteki chcesz powielić książkę: ");
		sc.nextLine();
		String name = sc.nextLine();
		if (libraries.containsKey(name.toUpperCase()) != true) {
			System.out.println("Nie ma takiej biblioteki");
		} else {
			Library library = libraries.get(name.toUpperCase());
			library.showLibrary();
			System.out.println("Którą książkę chcesz powielić? Podaj index: ");
			int choosenIndex = sc.nextInt();
			if (libraries.get(name.toUpperCase()).doesExist(choosenIndex) == false) {
				System.out.println("Nie ma książki o takim indeksie w danej biliotece");
			} else {
				libraries.get(name.toUpperCase()).multiplyBook(choosenIndex-1);
				System.out.println("Powielono podaną książkę");
			}
		}
	}

	public void deleteBook() {
		System.out.println("Dostępne biblioteki: ");
		displayLibraries();
		System.out.println("Z której biblioteki chcesz usunąć książkę: ");
		sc.nextLine();
		String name = sc.nextLine();
		if (libraries.containsKey(name.toUpperCase()) != true) {
			System.out.println("Nie ma takiej biblioteki");
		} else {
			Library library = libraries.get(name.toUpperCase());
			library.showLibrary();
			System.out.println("Którą książkę chcesz usunąć? Podaj index: ");
			int choosenIndex = sc.nextInt();
			if (libraries.get(name.toUpperCase()).doesExist(choosenIndex-1)==false) {
				System.out.println("Nie ma książki o takim indeksie w danej biliotece");
			} else {
				libraries.get(name.toUpperCase()).deleteBook(choosenIndex-1);
				System.out.println("Usunięto podaną książkę");
			}
		}
	}

	public void displayLibraries() {
		for (String name : libraries.keySet()) {
			System.out.println(name);
		}
	}

	public static void main(String[] args) {
		LibraryRunner librun = new LibraryRunner();
		librun.turnOn();
		System.out.println("----- KONIEC -----");
	}
}

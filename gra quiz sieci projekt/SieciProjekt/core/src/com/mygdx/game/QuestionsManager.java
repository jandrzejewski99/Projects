package com.mygdx.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class QuestionsManager {
    private final ArrayList<Question> questions = new ArrayList<>() {{
        add(new Question("Skąd pochodzi Conan Barbarzyńca?", "Z Mordory", "Z Cimmerii", "Z Rivii", "Z Oz", "b"));
        add(new Question("Odrażający drab z Kabaretu Starszych Panów dubeltówkę weźmie, wyjdzie i...", "rach-ciach!", "bum w brzuch.", "z rur dwóch.", "buch, buch!", "b"));
        add(new Question("Komiksowym 'dzieckiem' rysownika Boba Kane'a jest...", "Superman", "Spider-Man", "Captain America", "Batman", "d"));
        add(new Question("Rybą nie jest...", "kraska", "krasnopiórka", "rozpiór", "świnka", "a"));
        add(new Question("Kto jest mistrzem tego samego oręża, w jakim specjalizowała się mitologiczna Artemida?", "Don Kichot", "Legolas", "Zorro", "Longinus Podbipięta", "b"));
        add(new Question("Który aktor urodził się w roku opatentowania kinematografu braci Lumiere?", "Humphrey Bogart", "Charlie Chaplin", "Rudolph Valentino", "Fred Astaire", "c"));
        add(new Question("Mowa w obronie poety Archiasza przeszła do historii jako jeden z najświetniejszych popisów retorycznych...", "Cycerona", "Izokratesa", "Demostenesa", "Kwintyliana", "a"));
        add(new Question("Kto był nadwornym malarzem króla Filipa IV Habsburga?", "Jacques-Louis David", "Diego Velázquez", "Jan van Eyck", "Marcello Bacciarelli", "b"));
        add(new Question("Likier maraskino produkuje się z maraski, czyli odmiany...", "figi", "gruszy", "wiśni", "jabłoni", "c"));
        add(new Question("Z gry na jakim instrumencie słynie Czesław Mozil?", "na ksylofonie", "na akordeonie", "na kornecie", "na djembe", "b"));
        add(new Question("Który utwór Juliusza Słowackiego napisany jest prozą?", "Godzina myśli", "Anhelli", "Arab", "W Szwajcarii", "b"));
        add(new Question("Płetwą grzbietową nie pruje wody...", "orka", "kosogon", "wal grenlandzki", "długoszpar", "c"));
    }};
    private Set<Integer> usedQuestions = new HashSet<>();

    public Question getRandomQuestion() {
        int randomIndex = ThreadLocalRandom.current().nextInt(0, questions.size() - 1);
        int startIndex = randomIndex;

        while (usedQuestions.contains(randomIndex)) {
            randomIndex++;
            if (randomIndex >= questions.size()) {
                randomIndex = 0;
            }

            if(randomIndex == startIndex) {
                System.out.println("Used all questions!");
                return null;
            }
        }

        usedQuestions.add(randomIndex);
        return questions.get(randomIndex);
    }

    public boolean isCorrect(Question question, String answer) {
        return question.getCorrectAnswer().equals(answer);
    }

    public void resetQuestions() {
        usedQuestions = new HashSet<>();
    }
}

# iFellowAPITests

Проект направлен на тестирование API Jira, RickAndMorty и ReqRes сайтов.
В проекте используется RestAssured, Cucumber, jUnit5, Allure.

В проекте используется полное логирование в консоль, также логи добавлены в Allure отчеты.
Запуск тестов Cucumber возможен через тестовый класс, расположенный в src/test/java/API/CucumberTest.java.
Также возможно добавление запуска Cucumber тестов через mvn test. Для этого необходимо раскомментировать в pom.xml зависимость junit-vintage-engine. При этом, в Allure отчете возможна дубликация пройденных тестов в связи с зависимостями jUnit5.

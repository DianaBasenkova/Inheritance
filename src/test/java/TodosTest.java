import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class TodosTest {
    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);


        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindMatchesSimpleTask() {
        //тестируем поиск по запросу если совпадения найдены
        SimpleTask simpleTask = new SimpleTask(1, "Срочный звонок");
        boolean expected = true;
        boolean actual = simpleTask.matches("Срочный");
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldNotFindMatchesSimpleTask() {
        SimpleTask simpleTask = new SimpleTask(1, "Плановая уборка");

        boolean expected = false;
        boolean actual = simpleTask.matches("Срочный");
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldFindMatchesEpic() {
        String[] subtasks = {"Срочный баг", "Обычный баг", "Дополнительная проверка", "Написать отчет"};
        Epic epic = new Epic(2, subtasks);
        Todos todos = new Todos();
        todos.add(epic);
        boolean expected = true;
        boolean actual = epic.matches("Срочный");

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldNotFindMatchesEpic() {
        String[] subtasks = {"Обычный баг", "Дополнительная проверка", "Написать отчет"};
        Epic epic = new Epic(2, subtasks);
        Todos todos = new Todos();
        todos.add(epic);
        boolean expected = false;
        boolean actual = epic.matches("Срочный");

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldFindMatchesMeeting() {
        //тестируем поиск по запросу если совпадения найдены
        Meeting meeting = new Meeting(3, "Срочный релиз", "Подключение к ЭДО", "Пн.12.00");

        boolean expected = true;
        boolean actual = meeting.matches("Срочный");
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldNotFindMatchesMeeting() {
        Meeting meeting = new Meeting(
                1, "Плановый релиз", "Подключение к ЭДО", "Пн.12.00");
        Todos todos = new Todos();
        todos.add(meeting);
        boolean expected = false;
        boolean actual = meeting.matches("Срочный");
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldFindIfOne() {

        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );
        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {meeting};
        Task[] actual = todos.search("Приложение");
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldNotFind() {

        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );
        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {};
        Task[] actual = todos.search("Заплатить за ипотеку");
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldFindIfTreeFromAll() {
        SimpleTask simpleTask = new SimpleTask(1, "Протестировать приложение");

        String[] subtasks = {"приложение", "Сайт", "Реклама"};
        Epic epic = new Epic(2, subtasks);
        Meeting meeting = new Meeting(
                3,
                "Релиз версии 1.0",
                "приложение Java",
                "Согласовать после тестирования"
        );
        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.search("приложение");
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldFindIfVoidQueryFromAll() {
        SimpleTask simpleTask = new SimpleTask(1, "Протестировать приложение");

        String[] subtasks = {"приложение", "Сайт", "Реклама"};
        Epic epic = new Epic(2, subtasks);
        Meeting meeting = new Meeting(
                3,
                "Релиз версии 1.0",
                "приложение Java",
                "Согласовать после тестирования"
        );
        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {};
        Task[] actual = todos.search("null");
        Assertions.assertArrayEquals(expected, actual);

    }
}







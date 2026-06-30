package main.java.test;


import main.java.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TimetableTest {

    @Test
    void testGetTrainingSessionsForDaySingleSession() {

        Timetable timetable = new Timetable();

        Group group =
                new Group(
                        "Акробатика для детей",
                        Age.CHILD,
                        60
                );

        Coach coach =
                new Coach(
                        "Васильев",
                        "Николай",
                        "Сергеевич"
                );

        TrainingSession session =
                new TrainingSession(
                        group,
                        coach,
                        DayOfWeek.MONDAY,
                        new TimeOfDay(13, 0)
                );

        timetable.addNewTrainingSession(session);

        assertEquals(
                1,
                timetable.getTrainingSessionsForDay(
                        DayOfWeek.MONDAY
                ).size()
        );

        assertTrue(
                timetable.getTrainingSessionsForDay(
                        DayOfWeek.TUESDAY
                ).isEmpty()
        );
    }

    @Test
    void testGetTrainingSessionsForDayMultipleSessions() {

        Timetable timetable = new Timetable();

        Coach coach =
                new Coach(
                        "Васильев",
                        "Николай",
                        "Сергеевич"
                );

        Group groupAdult =
                new Group(
                        "Акробатика для взрослых",
                        Age.ADULT,
                        90
                );

        Group groupChild =
                new Group(
                        "Акробатика для детей",
                        Age.CHILD,
                        60
                );

        TrainingSession session1 =
                new TrainingSession(
                        groupAdult,
                        coach,
                        DayOfWeek.THURSDAY,
                        new TimeOfDay(20, 0)
                );

        TrainingSession session2 =
                new TrainingSession(
                        groupChild,
                        coach,
                        DayOfWeek.THURSDAY,
                        new TimeOfDay(13, 0)
                );

        timetable.addNewTrainingSession(session1);
        timetable.addNewTrainingSession(session2);

        List<TrainingSession> result =
                timetable.getTrainingSessionsForDay(
                        DayOfWeek.THURSDAY
                );

        assertEquals(2, result.size());

        assertEquals(
                13,
                result.get(0).getTimeOfDay().getHours()
        );

        assertEquals(
                20,
                result.get(1).getTimeOfDay().getHours()
        );
    }

    @Test
    void testGetTrainingSessionsForDayAndTime() {

        Timetable timetable = new Timetable();

        Group group =
                new Group(
                        "Акробатика для детей",
                        Age.CHILD,
                        60
                );

        Coach coach =
                new Coach(
                        "Васильев",
                        "Николай",
                        "Сергеевич"
                );

        TrainingSession session =
                new TrainingSession(
                        group,
                        coach,
                        DayOfWeek.MONDAY,
                        new TimeOfDay(13, 0)
                );

        timetable.addNewTrainingSession(session);

        assertEquals(
                1,
                timetable.getTrainingSessionsForDayAndTime(
                        DayOfWeek.MONDAY,
                        new TimeOfDay(13, 0)
                ).size()
        );

        assertTrue(
                timetable.getTrainingSessionsForDayAndTime(
                        DayOfWeek.MONDAY,
                        new TimeOfDay(14, 0)
                ).isEmpty()
        );
    }

    @Test
    void testSeveralTrainingsAtSameTime() {

        Timetable timetable = new Timetable();

        Coach coach =
                new Coach(
                        "Иванов",
                        "Иван",
                        "Иванович"
                );

        TrainingSession first =
                new TrainingSession(
                        new Group("Группа 1",
                                Age.CHILD,
                                60),
                        coach,
                        DayOfWeek.MONDAY,
                        new TimeOfDay(10, 0)
                );

        TrainingSession second =
                new TrainingSession(
                        new Group("Группа 2",
                                Age.CHILD,
                                60),
                        coach,
                        DayOfWeek.MONDAY,
                        new TimeOfDay(10, 0)
                );

        timetable.addNewTrainingSession(first);
        timetable.addNewTrainingSession(second);

        assertEquals(
                2,
                timetable.getTrainingSessionsForDayAndTime(
                        DayOfWeek.MONDAY,
                        new TimeOfDay(10, 0)
                ).size()
        );
    }

    @Test
    void testEmptyTimetable() {

        Timetable timetable = new Timetable();

        assertTrue(
                timetable.getTrainingSessionsForDay(
                        DayOfWeek.MONDAY
                ).isEmpty()
        );
    }

    @Test
    void testDifferentDays() {

        Timetable timetable = new Timetable();

        Coach coach =
                new Coach(
                        "Иванов",
                        "Иван",
                        "Иванович"
                );

        timetable.addNewTrainingSession(
                new TrainingSession(
                        new Group(
                                "Группа",
                                Age.CHILD,
                                60
                        ),
                        coach,
                        DayOfWeek.MONDAY,
                        new TimeOfDay(10, 0)
                )
        );

        timetable.addNewTrainingSession(
                new TrainingSession(
                        new Group(
                                "Группа",
                                Age.CHILD,
                                60
                        ),
                        coach,
                        DayOfWeek.FRIDAY,
                        new TimeOfDay(10, 0)
                )
        );

        assertEquals(
                1,
                timetable.getTrainingSessionsForDay(
                        DayOfWeek.MONDAY
                ).size()
        );

        assertEquals(
                1,
                timetable.getTrainingSessionsForDay(
                        DayOfWeek.FRIDAY
                ).size()
        );
    }


}

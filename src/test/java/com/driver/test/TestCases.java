package com.driver.test;

import com.driver.*;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

public class TestCases {

    @Test
    public void testEmail1(){
        Email email = new Email("accio@gmail.com");
        assert (email.getEmailId().equals("accio@gmail.com"));
        email.changePassword("Accio@123", "X12@");
        assert (email.getPassword().equals("Accio@123"));
        email.changePassword("ioAbn@123", "V1asdf2@v");
        assert (email.getPassword().equals("Accio@123"));
        email.changePassword("Accio@123", "V#1234567");
        assert (email.getPassword().equals("Accio@123"));
        email.changePassword("Accio@123", "V#abcdef");
        assert (email.getPassword().equals("Accio@123"));
        email.changePassword("Accio@123", "Va1234567");
        assert (email.getPassword().equals("Accio@123"));
        email.changePassword("Accio@123", "Va#1234567");
        assert (email.getPassword().equals("Va#1234567"));
        email.changePassword("Va#1234567", "a#1234567");
        assert (email.getPassword().equals("Va#1234567"));
    }

    @Test
    public void testGmail1() {
        Gmail gmail = new Gmail("accio@gmail.com", 3);
        assert (gmail.getEmailId().equals("accio@gmail.com"));
        gmail.changePassword("Accio@123", "Va#1234567");
        assert (gmail.getPassword().equals("Va#1234567"));
        gmail.changePassword("Va#1234567", "a#1234567");
        assert (gmail.getPassword().equals("Va#1234567"));

        assert(gmail.getInboxCapacity() == 3);
    }

    @Test
    public void testGmail2() throws ParseException {
        Gmail gmail = new Gmail("accio@hmail.com", 4);
        gmail.receiveMail(new SimpleDateFormat("dd/MM/yyyy").parse("02/10/2022"), "A", "a");
        gmail.receiveMail(new SimpleDateFormat("dd/MM/yyyy").parse("12/10/2022"), "B", "b");
        gmail.receiveMail(new SimpleDateFormat("dd/MM/yyyy").parse("13/10/2022"), "A", "c");
        gmail.receiveMail(new SimpleDateFormat("dd/MM/yyyy").parse("13/10/2022"), "A", "d");
        gmail.receiveMail(new SimpleDateFormat("dd/MM/yyyy").parse("14/11/2022"), "C", "e");
        assert(gmail.getTrashSize() == 1);
        assert(gmail.findOldestMessage().equals("b"));
        assert(gmail.findLatestMessage().equals("e"));
        gmail.receiveMail(new SimpleDateFormat("dd/MM/yyyy").parse("15/11/2022"), "C", "f");
        assert(gmail.getTrashSize() == 2);
        assert(gmail.findOldestMessage().equals("c"));
        assert(gmail.findLatestMessage().equals("f"));
    }

    @Test
    public void testGmail3() throws ParseException {
        Gmail gmail = new Gmail("accio@hmail.com", 4);
        gmail.receiveMail(new SimpleDateFormat("dd/MM/yyyy").parse("02/10/2022"), "A", "a");
        gmail.receiveMail(new SimpleDateFormat("dd/MM/yyyy").parse("12/10/2022"), "B", "b");
        gmail.receiveMail(new SimpleDateFormat("dd/MM/yyyy").parse("13/10/2022"), "A", "c");
        gmail.receiveMail(new SimpleDateFormat("dd/MM/yyyy").parse("13/10/2022"), "A", "d");
        gmail.receiveMail(new SimpleDateFormat("dd/MM/yyyy").parse("14/11/2022"), "C", "e");
        assert(gmail.getTrashSize() == 1);
        assert(gmail.findOldestMessage().equals("b"));
        assert(gmail.findLatestMessage().equals("e"));
        gmail.receiveMail(new SimpleDateFormat("dd/MM/yyyy").parse("15/11/2022"), "C", "f");
        assert(gmail.getTrashSize() == 2);
        assert(gmail.findOldestMessage().equals("c"));
        assert(gmail.findLatestMessage().equals("f"));
        gmail.deleteMail("a");
        gmail.deleteMail("b");
        gmail.deleteMail("c");
        assert(gmail.getTrashSize() == 3);
        assert(gmail.findOldestMessage().equals("d"));
        assert(gmail.findLatestMessage().equals("f"));
        gmail.emptyTrash();
        assert(gmail.getTrashSize() == 0);
        assert(gmail.findOldestMessage().equals("d"));
        assert(gmail.findLatestMessage().equals("f"));
        assert(gmail.getInboxSize() == 3);
        gmail.deleteMail("d");
        assert(gmail.getTrashSize() == 1);
        assert(gmail.findOldestMessage().equals("e"));
        assert(gmail.findLatestMessage().equals("f"));
        assert(gmail.getInboxSize() == 2);
        gmail.deleteMail("e");
        assert(gmail.getTrashSize() == 2);
        assert(gmail.findOldestMessage().equals("f"));
        assert(gmail.findLatestMessage().equals("f"));
        assert(gmail.getInboxSize() == 1);
        gmail.deleteMail("f");
        assert(gmail.getTrashSize() == 3);
        assert(gmail.findOldestMessage() == null);
        assert(gmail.findLatestMessage() == null);
        assert(gmail.getInboxSize() == 0);
    }

    @Test
    public void testGmail4() throws ParseException {
        Gmail gmail = new Gmail("accio@hmail.com", 6);
        gmail.receiveMail(new SimpleDateFormat("dd/MM/yyyy").parse("02/10/2022"), "A", "a");
        gmail.receiveMail(new SimpleDateFormat("dd/MM/yyyy").parse("12/10/2022"), "B", "b");
        gmail.receiveMail(new SimpleDateFormat("dd/MM/yyyy").parse("13/10/2022"), "A", "c");
        gmail.receiveMail(new SimpleDateFormat("dd/MM/yyyy").parse("13/10/2022"), "A", "d");
        gmail.receiveMail(new SimpleDateFormat("dd/MM/yyyy").parse("14/11/2022"), "C", "e");
        gmail.receiveMail(new SimpleDateFormat("dd/MM/yyyy").parse("15/11/2022"), "C", "f");
        assert(gmail.findMailsBetweenDates(new SimpleDateFormat("dd/MM/yyyy").parse("02/10/2022"), new SimpleDateFormat("dd/MM/yyyy").parse("02/10/2022")) == 1);
        assert(gmail.findMailsBetweenDates(new SimpleDateFormat("dd/MM/yyyy").parse("02/10/2022"), new SimpleDateFormat("dd/MM/yyyy").parse("12/10/2022")) == 2);
        assert(gmail.findMailsBetweenDates(new SimpleDateFormat("dd/MM/yyyy").parse("12/10/2022"), new SimpleDateFormat("dd/MM/yyyy").parse("13/10/2022")) == 3);
        assert(gmail.findMailsBetweenDates(new SimpleDateFormat("dd/MM/yyyy").parse("13/10/2022"), new SimpleDateFormat("dd/MM/yyyy").parse("13/10/2022")) == 2);
        assert(gmail.findMailsBetweenDates(new SimpleDateFormat("dd/MM/yyyy").parse("14/10/2022"), new SimpleDateFormat("dd/MM/yyyy").parse("13/11/2022")) == 0);
        assert(gmail.findMailsBetweenDates(new SimpleDateFormat("dd/MM/yyyy").parse("14/11/2022"), new SimpleDateFormat("dd/MM/yyyy").parse("15/11/2022")) == 2);
        assert(gmail.findMailsBetweenDates(new SimpleDateFormat("dd/MM/yyyy").parse("02/10/2000"), new SimpleDateFormat("dd/MM/yyyy").parse("02/10/2099")) == 6);
        assert(gmail.findMailsBetweenDates(new SimpleDateFormat("dd/MM/yyyy").parse("02/10/2022"), new SimpleDateFormat("dd/MM/yyyy").parse("15/11/2022")) == 6);
        assert(gmail.findMailsBetweenDates(new SimpleDateFormat("dd/MM/yyyy").parse("15/11/2022"), new SimpleDateFormat("dd/MM/yyyy").parse("15/11/2022")) == 1);
        assert(gmail.findMailsBetweenDates(new SimpleDateFormat("dd/MM/yyyy").parse("12/10/2022"), new SimpleDateFormat("dd/MM/yyyy").parse("15/11/2022")) == 5);
        assert(gmail.findMailsBetweenDates(new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2022"), new SimpleDateFormat("dd/MM/yyyy").parse("14/10/2022")) == 3);
    }

    @Test
    public void testWorkspace1() throws ParseException {
        Workspace workspace = new Workspace("accio@hmail.com");
        assert(workspace.getInboxCapacity() == Integer.MAX_VALUE);
        workspace.receiveMail(new SimpleDateFormat("dd/MM/yyyy").parse("02/10/2022"), "A", "a");
        workspace.receiveMail(new SimpleDateFormat("dd/MM/yyyy").parse("12/10/2022"), "B", "b");
        workspace.receiveMail(new SimpleDateFormat("dd/MM/yyyy").parse("13/10/2022"), "A", "c");
        workspace.receiveMail(new SimpleDateFormat("dd/MM/yyyy").parse("13/10/2022"), "A", "d");
        workspace.receiveMail(new SimpleDateFormat("dd/MM/yyyy").parse("14/11/2022"), "C", "e");
        assert(workspace.getTrashSize() == 0);
        assert(workspace.getInboxSize() == 5);
        assert(workspace.findOldestMessage().equals("a"));
        assert(workspace.findLatestMessage().equals("e"));
        workspace.receiveMail(new SimpleDateFormat("dd/MM/yyyy").parse("15/11/2022"), "C", "f");
        assert(workspace.getTrashSize() == 0);
        assert(workspace.getInboxSize() == 6);
        assert(workspace.findOldestMessage().equals("a"));
        assert(workspace.findLatestMessage().equals("f"));

        workspace.addMeeting(new Meeting(LocalTime.parse("01:00"), LocalTime.parse("02:00")));
        assert(workspace.findMaxMeetings() == 1);
        workspace.addMeeting(new Meeting(LocalTime.parse("03:00"), LocalTime.parse("04:00")));
        assert(workspace.findMaxMeetings() == 2);
        workspace.addMeeting(new Meeting(LocalTime.parse("00:00"), LocalTime.parse("06:00")));
        assert(workspace.findMaxMeetings() == 2);
        workspace.addMeeting(new Meeting(LocalTime.parse("05:00"), LocalTime.parse("07:00")));
        assert(workspace.findMaxMeetings() == 3);
        workspace.addMeeting(new Meeting(LocalTime.parse("08:00"), LocalTime.parse("09:00")));
        assert(workspace.findMaxMeetings() == 4);
        workspace.addMeeting(new Meeting(LocalTime.parse("05:00"), LocalTime.parse("09:00")));
        assert(workspace.findMaxMeetings() == 4);
    }

}

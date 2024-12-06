package System;

import java.io.IOException;
import java.util.Scanner;

public class TimeManagement implements Design_Animation{
    Scanner scan = new Scanner(System.in);

    Design_Animation.design LENGTH = Design_Animation.design.LENGTH;
    Design_Animation.design DEFAULT_ANIMATION_SPEED = Design_Animation.design.DEFAULT_ANIMATION_SPEED;
    Design_Animation.design DEFAULT_DELAY = Design_Animation.design.DEFAULT_DELAY;
    Design_Animation.design DEFAULT_NO_COLUMNS = Design_Animation.design.DEFAULT_NO_COLUMNS;
    Design_Animation.design LONG_ANIMATION_SPEED = Design_Animation.design.LONG_ANIMATION_SPEED;

    public void startUp(){
        String nickname;

        clear();
        printNewlines_withAnimation_alignment(18, DEFAULT_ANIMATION_SPEED.getValue(), DEFAULT_NO_COLUMNS.getValue(), "Hi I'm Nexus (Time Management), May I know how should address you?");
        printNewlines_aligment(2, DEFAULT_NO_COLUMNS.getValue() - 62, "--> ");
        nickname = scan.nextLine();

        systemMenu(nickname);
    }

    private void systemMenu(String nickname){
        String[] menu = {"ANALYZE MY TIME ON ACTIVITIES", "BYE"};

        LENGTH.setValue(50);
        
        OUTER:
        do{
            clear();
            printNewlines_aligment(16, DEFAULT_NO_COLUMNS.getValue(), "-".repeat(LENGTH.getValue()));
            lnprint_alignment(DEFAULT_NO_COLUMNS.getValue(), "NEXUS PROMPT");
            lnprint_alignmentln(DEFAULT_NO_COLUMNS.getValue(), "-".repeat(LENGTH.getValue()));
            
            for(int i = 0; i < menu.length; i++) lnprintWithTabs_spaces(7, 6, (i + 1) + ". " + menu[i]);
            printNewlines_aligment(2, DEFAULT_NO_COLUMNS.getValue(), "-".repeat(LENGTH.getValue()));

            printNewlines_withAnimation_alignment(2, DEFAULT_ANIMATION_SPEED.getValue(), DEFAULT_NO_COLUMNS.getValue() - 20, nickname + ", ENTER HERE -->  ");
            
            try {
                int choice = Integer.parseInt(scan.nextLine());

                switch(choice){
                    case 1 -> setProgramData(nickname);
                    case 2 -> {
                        printNewlines_withAnimation_alignment(2, LONG_ANIMATION_SPEED.getValue(), DEFAULT_NO_COLUMNS.getValue(), "Thank you for using Nexus! See you next time " + nickname + "!");
                        threading(DEFAULT_DELAY.getValue());
                        clear();
                        break OUTER;
                    }

                    default -> printNewlines_withAnimation_alignment(2, DEFAULT_ANIMATION_SPEED.getValue(), DEFAULT_NO_COLUMNS.getValue(), "Hmm, that doesn't seem right. Nexus only understand the two prompts.");
                }
            } catch (NumberFormatException e) {
                printNewlines_withAnimation_alignment(2, DEFAULT_ANIMATION_SPEED.getValue(), DEFAULT_NO_COLUMNS.getValue(), "Uh-oh! Nexus only understands numbers for this prompt section right now.");
            }

            threading(DEFAULT_DELAY.getValue());
        } while(true);
    }

    private void setProgramData(String nickName){
        String[] activityNames = {"Study", "Sleep", "Leisure Time", "Exercise"};
        double[] minHours = {2, 8, 2, 0.5};
        double[] maxHours = {8, 10, 5, 2};

        String[][] messages_feedbacks = {
            {
                """
                \t\t    Don't panic! Even if you haven't studied much, there's still hope. Dedicate at least 2 hours a day to focused study
                \t\t    sessions. Prioritize the most important topics and use effective techniques like active recall and spaced repetition.
                \t\t    Seek help from teachers, tutors, or online resources. Stay positive and believe in yourself. Remember, every bit of
                \t\t    effort counts.""",
                
                """
                \t\t    Don't overdo it! While consistent study is important, overstudying can lead to burnout and decreased productivity.
                \t\t    Balance your study time with rest, relaxation, and other activities you enjoy. Prioritize your tasks, take breaks,
                \t\t    and practice self-care. Remember, a well-rested and focused mind is more effective than an exhausted one."""
            },

            {
                """
                \t\t    I noticed you didn't quite reach the recommended hours of sleep. Sleep is so important for your energy, focus, and
                \t\t    overall health. I know life can get busy, but maybe prioritizing your rest when you can could really make a
                \t\t    difference in how you feel and perform.""",

                """
                \t\t    Getting enough sleep is important, but sleeping too much can sometimes leave you feeling sluggish or disrupt your
                \t\t    routine. It might help to aim for a balanced amount of sleep to stay energized and maintain productivity throughout
                \t\t    the day."""
            },

            {
                """
                \t\t    Remember that leisure time isn't a waste-it's a vital part of staying healthy and productive. While it's commendable
                \t\t    that you've been so focused on your responsibilities, constantly pushing without rest can drain your energy and
                \t\t    creativity. Takin even a short break to do something you enjoy can refresh your mind, reduce stress, and help you
                \t\t    perform better.""",

                """
                \t\t    it's great that you're taking time to enjoy yourself, but remember that balance is key to long-term success and
                \t\t    well-being. Excessive leisure can sometimes distract from your goals or responsibilities, so it's importatn to manage
                \t\t    your time wisely. Reflect on your priorities and consider dedicating a bit more energy to productive activities that
                \t\t    align with your ambitions."""
            },

            {
                """
                \t\t    Exercise is essential for your health and focus. Aim to to include at least 30 minutes of activity daily-it could be a
                \t\t    brisk walk, stretching, or a quick workout. Even short sessions during study breaks can boost your energy and help you
                \t\t    stay sharp for your academics.""",

                """
                \t\t    While staying active is great, be careful not to overdo it. Exercising for more than 2 hours daily can lead to fatigue
                \t\t    and effect your studies or recovery. Balance your routinge to include rest and focus on academics while maintaining a
                \t\t    healthy lifestyle."""
            },
        };

        getUserData(nickName, activityNames, minHours, maxHours, messages_feedbacks);
    }

    public void getUserData(String nickName, String[] activityNames, double[] minHours, double[] maxHours, String[][] feedBacks_Message){
        
        Activity[] activities = new Activity[activityNames.length];
        for (int i = 0; i < activities.length; i++) activities[i] = new Activity(activityNames[i], minHours[i], maxHours[i], feedBacks_Message[i]);
        
        double totalHours = 0;

        int count = 0;
        while(count < activities.length){
            clear();
            printNewlines_aligment(16, DEFAULT_NO_COLUMNS.getValue(), "How many hours did you spent on following actitivies:");
            lnprint_alignment(DEFAULT_NO_COLUMNS.getValue(), "-".repeat(60));
            
            for(int i = 0; i < count; i++) printWithNewlines_tabs_spaces(2, 7, 2, activities[i].activityName + " : " + activities[i].hours);

            try {
                printWithNewlines_tabs_spaces(2, 7, 2, activities[count].activityName + " : ");
                activities[count].hours = Integer.parseInt(scan.nextLine());
                totalHours += activities[count].hours;
                count++;
            }catch (NumberFormatException e) {
                printNewlines_withAnimation_alignment(2, DEFAULT_ANIMATION_SPEED.getValue(), DEFAULT_NO_COLUMNS.getValue(), "Hmm, that doesn't look like a number. Nexus loves numbers! Please try again.");
                threading(DEFAULT_DELAY.getValue());
            }
        }

        analysis(totalHours, activities, nickName);
        feedBackForUser(activities, nickName);
    }

    private void analysis(double totalHours, Activity[] activities, String nickName){
        clear();

        LENGTH.setValue(60);

        printNewlines_withAnimation_alignment(15, DEFAULT_ANIMATION_SPEED.getValue(), DEFAULT_NO_COLUMNS.getValue(), "NEXUS ANALYSIS");
        lnprintWithAnimation_alignment(DEFAULT_ANIMATION_SPEED.getValue(), DEFAULT_NO_COLUMNS.getValue(), "-".repeat(LENGTH.getValue()));

        if (totalHours > 24) lnprintWithAnimation_alignment(DEFAULT_ANIMATION_SPEED.getValue(), DEFAULT_NO_COLUMNS.getValue(), "NOTE: Total reported hours exceed 24 hours in a Day");

        double percentage;
        for(Activity activity : activities){
            percentage = (activity.hours / totalHours) * 100;
            printWithNewlines_tabs_spaces_animation(2, 7, 2, DEFAULT_ANIMATION_SPEED.getValue(),  activity.activityName + " : " + activity.hours + " ("  + String.format("%.2f", percentage) + "%)");
        }

        printNewlines_withAnimation_alignment(2, DEFAULT_ANIMATION_SPEED.getValue(), DEFAULT_NO_COLUMNS.getValue(), "-".repeat(LENGTH.getValue()));
        clickAny(nickName);
    }

    private void feedBackForUser(Activity[] activities, String nickname){
        String message;

        for(Activity activity : activities){
            clear();

            if(activity.hours < activity.min) message = activity.messages[0];
            else message = activity.messages[1];

            
            printNewlines_aligmentln(16, DEFAULT_NO_COLUMNS.getValue(), "Nexus Message for You (" + activity.activityName + ")");

            lnprintWithAnimation(DEFAULT_ANIMATION_SPEED.getValue() + 20, message);
            clickAny(nickname);
        }
    }

    private void clickAny(String nickname){
        printNewlines_withAnimation_alignment(2, DEFAULT_ANIMATION_SPEED.getValue(), DEFAULT_NO_COLUMNS.getValue(), nickname + ", click any character to continue!");
        scan.nextLine();
    }
}

class Activity{
    protected String activityName;
    protected double max;
    protected double min;
    protected double hours;
    protected String[] messages;

    protected Activity(String activityName, double min, double max, String[] messages){
        this.activityName = activityName;
        this.max = max;
        this.min = min;
        this.hours = 0;
        this.messages = messages;
    }
}

interface Design_Animation{
    enum design{
        LENGTH(0), DEFAULT_ANIMATION_SPEED(30), LONG_ANIMATION_SPEED(60), DEFAULT_DELAY(800), DEFAULT_NO_COLUMNS(156);
    
        int value;
    
        private design(int value){
            this.value = value;
        }
    
        protected void setValue(int value){
            this.value = value;
        }
    
        protected int getValue(){
            return value;
        }
    }

    default void printNewlines_withAnimation_alignment(int newline, int miliseconds, int columns, String text){
        char[] texts = text.toCharArray();

        for (int i = 0; i < newline; i++) System.out.println();

        System.out.printf("%" + (columns - text.length()) / 2 + "s", "");

        for(char t : texts){
            System.out.print(t);
            threading(miliseconds);
        }
    }

    default void lnprintWithAnimation_alignment(int miliseconds, int columns, String text){
        char[] texts = text.toCharArray();

        System.out.printf("\n%" + (columns - text.length()) / 2 + "s", "");

        for(char t : texts){
            System.out.print(t);
            threading(miliseconds);
        }
    }

    default void printNewlines_aligment(int newline, int columns, String text){
        for(int i = 0; i < newline; i++) System.out.println();

        System.out.printf("%" + (columns - text.length()) / 2 + "s%s", "", text);
    }

    default void printNewlines_aligmentln(int newline, int columns, String text){
        for(int i = 0; i < newline; i++) System.out.println();

        System.out.printf("%" + (columns - text.length()) / 2 + "s%s\n", "", text);
    }

    default void lnprint_alignment(int columns, String text){
        System.out.printf("\n%" + (columns - text.length()) / 2 + "s%s", "", text);
    }

    default void print_alignmentln(int columns, String text){
        System.out.printf("%" + (columns - text.length()) / 2 + "s%s\n", "", text);
    }

    default void lnprint_alignmentln(int columns, String text){
        System.out.printf("\n%" + (columns - text.length()) / 2 + "s%s\n", "", text);
    }

    default void printWithNewlines_tabs_spaces(int newlines, int tabs, int spaces, String text){
        for(int i = 0; i < newlines; i++ )System.out.println();
        for(int i = 0; i < tabs; i++) System.out.print("\t");
        for(int i = 0; i < spaces; i++) System.out.print("\s");

        System.out.print(text);
    }

    default void lnprintWithTabs_spaces(int tabs, int spaces, String text){
        System.out.println();
        for(int i = 0; i < tabs; i++) System.out.print("\t");
        for(int i = 0; i < spaces; i++) System.out.print("\s");

        System.out.print(text);
    }

    default void printWithNewlines_tabs_spaces_animation(int newlines, int tabs, int spaces, int miliseconds, String text){
        char[] texts = text.toCharArray();
        
        for(int i = 0; i < newlines; i++ )System.out.println();
        for(int i = 0; i < tabs; i++) System.out.print("\t");
        for(int i = 0; i < spaces; i++) System.out.print("\s");

        for(char t : texts){
            System.out.print(t);
            threading(miliseconds);
        }
    }

    default void lnprintWithAnimation(int miliseconds, String text){
        char[] texts = text.toCharArray();

        System.out.println();
        for(char t : texts){
            System.out.print(t);
            threading(miliseconds);
        }
    }

    default void threading(int miliseconds){
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
        }
    }

    default void clear(){
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
        }
    }
}

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TimeManagement implements Design_Animation {
  Scanner scan = new Scanner(System.in);

  Design_Animation.design LENGTH = Design_Animation.design.LENGTH;
  Design_Animation.design DEFAULT_ANIMATION_SPEED = Design_Animation.design.DEFAULT_ANIMATION_SPEED;
  Design_Animation.design SHORT_DELAY = Design_Animation.design.SHORT_DELAY;
  Design_Animation.design DEFAULT_DELAY = Design_Animation.design.DEFAULT_DELAY;
  Design_Animation.design DEFAULT_NO_COLUMNS = Design_Animation.design.DEFAULT_NO_COLUMNS;

  public void startUp() {
    clear();
    printNewlines_withAnimation_alignment(
        18,
        DEFAULT_ANIMATION_SPEED.getValue(),
        DEFAULT_NO_COLUMNS.getValue(),
        "Hi I'm Deyns, a Time Management System. May I know how should address you?");
    printNewlines_aligment(2, DEFAULT_NO_COLUMNS.getValue() - 62, "--> ");
    systemMenu(scan.nextLine());
  }

  private void systemMenu(String nickname) {
    String[] menu = {"ANALYZE MY TIME ON ACTIVITIES", "BYE"};

    LENGTH.setValue(50);

    OUTER:
    do {
      clear();
      printNewlines_aligment(16, DEFAULT_NO_COLUMNS.getValue(), "-".repeat(LENGTH.getValue()));
      lnprint_alignment(DEFAULT_NO_COLUMNS.getValue(), "DEYNS PROMPT");
      lnprint_alignmentln(DEFAULT_NO_COLUMNS.getValue(), "-".repeat(LENGTH.getValue()));

      for (int i = 0; i < menu.length; i++) lnprintWithTabs_spaces(7, 6, (i + 1) + ". " + menu[i]);
      printNewlines_aligment(2, DEFAULT_NO_COLUMNS.getValue(), "-".repeat(LENGTH.getValue()));

      printNewlines_withAnimation_alignment(
          2,
          DEFAULT_ANIMATION_SPEED.getValue(),
          DEFAULT_NO_COLUMNS.getValue() - 20,
          nickname + ", ENTER HERE -->  ");

      try {
        switch (Integer.parseInt(scan.nextLine())) {
          case 1 -> setProgramData(nickname);
          case 2 -> {
            userFeedBack(nickname);
            break OUTER; // label break statement
          }

          default ->
              printNewlines_withAnimation_alignment(
                  2,
                  DEFAULT_ANIMATION_SPEED.getValue(),
                  DEFAULT_NO_COLUMNS.getValue(),
                  "Hmm, that doesn't seem right. Deyns only understand the two prompts.");
        }
      } catch (NumberFormatException e) {
        printNewlines_withAnimation_alignment(
            2,
            DEFAULT_ANIMATION_SPEED.getValue(),
            DEFAULT_NO_COLUMNS.getValue(),
            "Uh-oh! Deyns only understands numbers for this prompt section right now.");
      }

      threading(DEFAULT_DELAY.getValue());
    } while (true);
  }

  private void setProgramData(String nickName) {
    String[][] activityNames = {
      {"Study"},
      {"Cooking", "Cleaning"},
      {"Voluntary", "Social Activities"},
      {"Sleep", "Exercise", "Self Entertainment"}
    };
    String[] worktypes = {"Productive", "Reproductive", "Community", "Personal"};

    double[] minHours = {5, 1, 1, 9};
    double[] maxHours = {8, 2, 2, 12};

    String[][] messages_feedbacks = {
      {
        """
        \t\t    Your recent productive hours did not meet the minimum requirement. Maintaining consistent productivity is crucial
        \t\t    for achieving your goals. If there were any challenges that impacted your focus or workflow, consider identifying
        \t\t    them and implementing strategies to overcome them. Deyns recommends reviewing your tasks, setting clear priorities,
        \t\t    and using breaks effectively to optimize your time. Let's work towards better results in the next cycle!\
        """,
        """
        \t\t    Your dedication and hard work are admirable, and it shows how committed you are to your responsibilities. But remember
        \t\t    your well-being is just as important. Pushing yourself beyond your limits can lead to burnout and take a toll on your
        \t\t    health and happiness. Allow yourself to rest, recharge, and find that balance. Taking time to care for yourself will
        \t\t    ultimately make you more effective and fulfilled in your work.\
        """
      },
      {
        """
        \t\t    Deyns noticed you didn't meet the minimum hours of reproductive work. I understand that balancing everything can be
        \t\t    tough, but these tasks are important for your growth and the well-being of those around you. Remember, even small
        \t\t    contributions make a difference.\
        """,
        """
        \t\t    Deyns really admire your dedication and the hard work you put into your responsibilities. However, I want to remind
        \t\t    you to take some time for yourself and find a healthy balance. Overextending yourself with reproductive work can
        \t\t    lead to burnout, so make sure you're also prioritizing rest and self-care. You deserve it!\
        """
      },
      {
        """
        \t\t    Deyns knows things can get busyt, and it's not always easy to meet your communitry work goals. Remember, every small
        \t\t    effort makes a difference, and your involvement truly matters. Completing these hours is not just about meeting a
        \t\t    requirement--it's chance to learn, grow, and make an impact. I believe in you and know you have what takes to reach
        \t\t    this goal. Keep going, and don't be affraid to reach out for support or advice. You've got this!\
        """,
        """
        \t\t    Your passion and willingness to go above and beyond are truly inspiring. It shows you deep commitment to making a
        \t\t    difference. However, it's important to recognize that self-care and getting boundaries are just as vital as the work
        \t\t    itself. Take this moment to assess how can you continue giving your best without compromising you health and energy.
        \t\t    Remember, you can achieve even greater things by working smart and staying balanced.\
        """
      },
      {
        """
        \t\t    Remember that setbacks happen to everyone. It's important not to let this moment define your commitment or abilities.
        \t\t    Reflect on what kept you from reaching your goal and use that insight to plan a better approach for next time. Every
        \t\t    day is a new opportunity to try again and improve. Your effort matters, and even when progress is slower than you'd
        \t\t    like, it's still progress. Stay persistent, learn from these experiences, and don't be too hard on yourself. You've
        \t\t    got this!\
        """,
        """
        \t\t    Working hard and giving your best effort is admirable, but remember that you're at your most productive and creative
        \t\t    when you're at most productive and creative when you're well-rested and healthy. It's okay to set boundaries and take
        \t\t    the time you need to recharge. Your health, energy, and happiness are just as important as your work, and taking care
        \t\t    of yourself will only help you perform better in the long run. Don't understimate the power of rest-it's not just a
        \t\t    break, it's an investment in your future success.\
        """
      }
    };

    getUserData(nickName, worktypes, activityNames, minHours, maxHours, messages_feedbacks);
  }

  private void getUserData(
      String nickName,
      String workTypes[],
      String[][] activityNames,
      double[] minHours,
      double[] maxHours,
      String[][] feedBacks_Message) {
    Activity[] activities = new Activity[workTypes.length];
    for (int i = 0; i < activities.length; i++)
      activities[i] =
          new Activity(
              workTypes[i], activityNames[i], minHours[i], maxHours[i], feedBacks_Message[i]);

    LENGTH.setValue(60);

    double totalHours = 0;
    int count = 0;
    while (count < activities.length) {
      for (int i = 0; i < activities[count].activityNames.length; i++) {
        clear();
        printNewlines_aligment(15, DEFAULT_NO_COLUMNS.getValue(), "-".repeat(LENGTH.getValue()));
        lnprint_alignment(DEFAULT_NO_COLUMNS.getValue(), "YOUR ACTIVITIES");
        lnprint_alignmentln(DEFAULT_NO_COLUMNS.getValue(), "-".repeat(LENGTH.getValue()));

        for (String activityName : activities[count].activityNames)
          lnprintWithTabs_spaces(7, 6, activityName);

        printNewlines_aligmentln(2, DEFAULT_NO_COLUMNS.getValue(), "-".repeat(LENGTH.getValue()));

        try {
          lnprintWithTabs_spaces(
              7, 6, "Time Spent on " + activities[count].activityNames[i] + " : ");
          activities[count].hours[i] = Double.parseDouble(scan.nextLine());
          activities[count].workType_hours += activities[count].hours[i];
        } catch (NumberFormatException e) {
          printNewlines_withAnimation_alignment(
              2,
              DEFAULT_ANIMATION_SPEED.getValue(),
              DEFAULT_NO_COLUMNS.getValue(),
              "Hmm, that doesn't look like a number. Deyns loves numbers! Please try again.");
          i--;
        }

        threading(SHORT_DELAY.getValue());
      }

      totalHours += activities[count].workType_hours;
      count++;
    }

    analysis(totalHours, activities, nickName);
  }

  private void analysis(double totalHours, Activity[] activities, String nickName) {
    clear();

    LENGTH.setValue(60);
    printNewlines_withAnimation_alignment(
        11,
        DEFAULT_ANIMATION_SPEED.getValue(),
        DEFAULT_NO_COLUMNS.getValue(),
        "-".repeat(LENGTH.getValue()));
    lnprintWithAnimation_alignment(
        DEFAULT_ANIMATION_SPEED.getValue(), DEFAULT_NO_COLUMNS.getValue(), "DEYNS ANALYSIS");
    lnprintWithAnimation_alignment(
        DEFAULT_ANIMATION_SPEED.getValue(),
        DEFAULT_NO_COLUMNS.getValue(),
        "-".repeat(LENGTH.getValue()));

    if (totalHours > 24)
      lnprintWithAnimation_alignment(
          DEFAULT_ANIMATION_SPEED.getValue(),
          DEFAULT_NO_COLUMNS.getValue(),
          "NOTE: Total reported hours exceed 24 hours in a Day.");

    for (Activity activity : activities) {
      printWithNewlines_tabs_spaces_animation(
          2,
          7,
          2,
          DEFAULT_ANIMATION_SPEED.getValue(),
          activity.workType
              + " Work : "
              + activity.workType_hours
              + " h"
              + " ("
              + String.format("%.2f", ((activity.workType_hours / totalHours) * 100))
              + "%)");
      for (int i = 0; i < activity.activityNames.length; i++)
        lnprint_tabs_spaces_animation(
            7,
            12,
            DEFAULT_ANIMATION_SPEED.getValue(),
            "- " + activity.activityNames[i] + " : " + activity.hours[i] + " h");
    }

    printNewlines_withAnimation_alignment(
        2,
        DEFAULT_ANIMATION_SPEED.getValue(),
        DEFAULT_NO_COLUMNS.getValue(),
        "-".repeat(LENGTH.getValue()));
    clickAny(nickName);

    feedBackForUser(activities, nickName);
  }

  private void feedBackForUser(Activity[] activities, String nickname) {
    String message;

    for (Activity activity : activities) {
      clear();

      message =
          activity.workType_hours < activity.min ? activity.messages[0] : activity.messages[1];

      printNewlines_aligmentln(
          16,
          DEFAULT_NO_COLUMNS.getValue(),
          "Deyns Message for You ("
              + activity.workType
              + " - Min : "
              + activity.min
              + ", Max : "
              + activity.max
              + ")");
      lnprintWithAnimation(DEFAULT_ANIMATION_SPEED.getValue() + 20, message);
      clickAny(nickname);
    }
  }

  private void userFeedBack(String nickname) {
    OUTER:
    do {
      clear();
      printNewlines_aligmentln(
          16,
          DEFAULT_NO_COLUMNS.getValue(),
          "Before you leave, could you take a moment to rate Deyns and share suggestions for"
              + " improvement? Your feeback helps Deyns grow!");

      lnprint_alignmentln(
          DEFAULT_NO_COLUMNS.getValue(), "Type 'SURE' to rate Deyns. 'LATER' to exit the System.");
      lnprintWithAnimation_alignment(
          DEFAULT_ANIMATION_SPEED.getValue() - 20, DEFAULT_NO_COLUMNS.getValue() - 16, "--> ");

      switch (scan.nextLine().toUpperCase()) {
        case "SURE" -> {
          ratingSection(nickname);
          break OUTER;
        }

        case "LATER" -> {
          programExit(nickname);
          break OUTER;
        }

        default ->
            lnprintWithAnimation_alignment(
                DEFAULT_ANIMATION_SPEED.getValue(),
                DEFAULT_NO_COLUMNS.getValue(),
                "Sorry, Deyns don't understand you. Please type valid prompt.");
      }

      threading(DEFAULT_DELAY.getValue());
    } while (true);
  }

  private void ratingSection(String nickname) {
    String[] ratingBasis = {
      "The system is difficult to use or unreliable",
      "Some functions work, but significant issues exist.",
      "The system meets basic expectations and works reliably",
      "The system is user-friendly and mostly bug-free",
      "The system exceeds expectations in functionality and design"
    };

    LENGTH.setValue(70);
    do {
      clear();
      printNewlines_aligment(12, DEFAULT_NO_COLUMNS.getValue(), "-".repeat(LENGTH.getValue()));
      lnprint_alignment(DEFAULT_NO_COLUMNS.getValue(), "RATE DEYNS");
      lnprint_alignmentln(DEFAULT_NO_COLUMNS.getValue(), "-".repeat(LENGTH.getValue()));

      for (int i = 0; i < ratingBasis.length; i++)
        lnprintlnWithTabs_spaces(6, 0, "(" + (i + 1) + ") " + ratingBasis[i]);

      lnprint_alignmentln(DEFAULT_NO_COLUMNS.getValue(), "-".repeat(LENGTH.getValue()));
      lnprint_alignment(DEFAULT_NO_COLUMNS.getValue() - 16, nickname + ", Enter Your Rate Here : ");

      try {
        int rate = Integer.parseInt(scan.nextLine());

        if (rate > 0 && rate <= ratingBasis.length) {
          userSuggestions(nickname, rate + " Rating");
          break;
        } else
          lnprintWithAnimation_alignment(
              DEFAULT_ANIMATION_SPEED.getValue(),
              DEFAULT_NO_COLUMNS.getValue(),
              "Invalid Rate! Only 1-5 rates is allowed.");
      } catch (NumberFormatException e) {
        lnprintWithAnimation_alignment(
            DEFAULT_ANIMATION_SPEED.getValue(),
            DEFAULT_NO_COLUMNS.getValue(),
            "Number answers are only applicable in this section.");
      }

      threading(DEFAULT_DELAY.getValue());
    } while (true);
  }

  private void userSuggestions(String nickname, String rating) {
    printNewlines_aligment(2, DEFAULT_NO_COLUMNS.getValue() - 24, "Your Suggestions : ");

    try (BufferedWriter writer =
        new BufferedWriter(new FileWriter("Java\\User_Feedbacks.txt", true))) {
      writer.append(nickname + " : " + rating + " : " + scan.nextLine() + "\n");
    } catch (IOException x) {
    }

    programExit(nickname);
  }

  private void programExit(String nickname) {
    lnprintWithAnimation_alignment(
        DEFAULT_ANIMATION_SPEED.getValue(),
        DEFAULT_NO_COLUMNS.getValue(),
        "Thank you for using Deyns! See you next time " + nickname + "!");
    threading(DEFAULT_DELAY.getValue());
    clear();
  }

  private void clickAny(String nickname) {
    printNewlines_withAnimation_alignment(
        2,
        DEFAULT_ANIMATION_SPEED.getValue(),
        DEFAULT_NO_COLUMNS.getValue(),
        nickname + ", click any character to continue!");
    scan.nextLine();
  }
}

class Activity {
  protected String workType;
  protected double workType_hours;
  protected String activityNames[];
  protected double max;
  protected double min;
  protected double hours[];
  protected String[] messages;

  protected Activity(
      String workType, String[] activityNames, double min, double max, String[] messages) {
    this.workType = workType;
    this.activityNames = activityNames;
    this.max = max;
    this.min = min;
    this.workType_hours = 0;
    this.hours = new double[activityNames.length];
    this.messages = messages;
  }
}

interface Design_Animation {
  enum design {
    LENGTH(0),
    DEFAULT_ANIMATION_SPEED(30),
    LONG_ANIMATION_SPEED(60),
    SHORT_DELAY(500),
    DEFAULT_DELAY(800),
    DEFAULT_NO_COLUMNS(156);

    int value;

    private design(int value) {
      this.value = value;
    }

    protected void setValue(int value) {
      this.value = value;
    }

    protected int getValue() {
      return value;
    }
  }

  default void printNewlines_withAnimation_alignment(
      int newline, int miliseconds, int columns, String text) {
    for (int i = 0; i < newline; i++) System.out.println();

    System.out.printf("%" + (columns - text.length()) / 2 + "s", "");

    for (char t : text.toCharArray()) {
      System.out.print(t);
      threading(miliseconds);
    }
  }

  default void lnprintWithAnimation_alignment(int miliseconds, int columns, String text) {
    System.out.printf("\n%" + (columns - text.length()) / 2 + "s", "");

    for (char t : text.toCharArray()) {
      System.out.print(t);
      threading(miliseconds);
    }
  }

  default void printNewlines_aligment(int newline, int columns, String text) {
    for (int i = 0; i < newline; i++) System.out.println();

    System.out.printf("%" + (columns - text.length()) / 2 + "s%s", "", text);
  }

  default void printNewlines_aligmentln(int newline, int columns, String text) {
    for (int i = 0; i < newline; i++) System.out.println();

    System.out.printf("%" + (columns - text.length()) / 2 + "s%s\n", "", text);
  }

  default void lnprint_alignment(int columns, String text) {
    System.out.printf("\n%" + (columns - text.length()) / 2 + "s%s", "", text);
  }

  default void print_alignmentln(int columns, String text) {
    System.out.printf("%" + (columns - text.length()) / 2 + "s%s\n", "", text);
  }

  default void lnprint_alignmentln(int columns, String text) {
    System.out.printf("\n%" + (columns - text.length()) / 2 + "s%s\n", "", text);
  }

  default void printWithNewlines_tabs_spaces(int newlines, int tabs, int spaces, String text) {
    for (int i = 0; i < newlines; i++) System.out.println();
    for (int i = 0; i < tabs; i++) System.out.print("\t");
    for (int i = 0; i < spaces; i++) System.out.print("\s");

    System.out.print(text);
  }

  default void lnprintWithTabs_spaces(int tabs, int spaces, String text) {
    System.out.println();
    for (int i = 0; i < tabs; i++) System.out.print("\t");
    for (int i = 0; i < spaces; i++) System.out.print("\s");

    System.out.print(text);
  }

  default void lnprintlnWithTabs_spaces(int tabs, int spaces, String text) {
    System.out.println();
    for (int i = 0; i < tabs; i++) System.out.print("\t");
    for (int i = 0; i < spaces; i++) System.out.print("\s");

    System.out.println(text);
  }

  default void lnprint_tabs_spaces_animation(int tabs, int spaces, int miliseconds, String text) {
    System.out.println();
    for (int i = 0; i < tabs; i++) System.out.print("\t");
    for (int i = 0; i < spaces; i++) System.out.print("\s");

    for (char t : text.toCharArray()) {
      System.out.print(t);
      threading(miliseconds);
    }
  }

  default void printWithNewlines_tabs_spaces_animation(
      int newlines, int tabs, int spaces, int miliseconds, String text) {
    for (int i = 0; i < newlines; i++) System.out.println();
    for (int i = 0; i < tabs; i++) System.out.print("\t");
    for (int i = 0; i < spaces; i++) System.out.print("\s");

    for (char t : text.toCharArray()) {
      System.out.print(t);
      threading(miliseconds);
    }
  }

  default void lnprintWithAnimation(int miliseconds, String text) {
    System.out.println();
    for (char t : text.toCharArray()) {
      System.out.print(t);
      threading(miliseconds);
    }
  }

  default void threading(int miliseconds) {
    try {
      Thread.sleep(miliseconds);
    } catch (InterruptedException e) {
    }
  }

  default void clear() {
    try {
      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    } catch (IOException | InterruptedException e) {
    }
  }
}

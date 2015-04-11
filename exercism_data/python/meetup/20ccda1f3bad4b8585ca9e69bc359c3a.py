from calendar import Calendar
from datetime import date


def meetup_day(year, month, weekday, nth_day):

    cal = Calendar()
    # Get the calendar for given month and year
    weeks_and_days = cal.monthdays2calendar(year, month)

    # Day string (Monday) --> number
    weekday_num = week_day_to_num(weekday)

    # Week string to number
    nth_week = dict(zip('1st 2nd 3rd 4th last'.split(), range(5)))

    final_day = 0

    if nth_day in nth_week:

        # Make the string an int
        week_num = nth_week[nth_day]

        # Check if there is a "Monday" on first week
        if weeks_and_days[0][weekday_num][0]:
            final_day = weeks_and_days[week_num][weekday_num][0]

        else:
            final_day = weeks_and_days[week_num + 1][weekday_num][0]

    elif nth_day == "teenth":

        # Check if in second week
        if weeks_and_days[1][weekday_num][0] >= 10:
            final_day = weeks_and_days[1][weekday_num][0]

        # Check if third week
        elif weeks_and_days[2][weekday_num][0] >= 10:
            final_day = weeks_and_days[2][weekday_num][0]

        else:
            final_day = weeks_and_days[3][weekday_num][0]

    return date(year, month, final_day)


def week_day_to_num(weekday):

    dic_of_days = dict(zip('Monday Tuesday Wednesday Thursday\
                            Friday Saturday Sunday'.split(), range(7)))
    return dic_of_days[weekday]

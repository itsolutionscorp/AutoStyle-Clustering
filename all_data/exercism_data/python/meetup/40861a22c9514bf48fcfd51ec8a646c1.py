"""
meetup.py: Program to calculate the date of meetups.
"""
import calendar
from datetime import date

DAYS = {
    'Monday': 0,
    'Tuesday': 1,
    'Wednesday': 2,
    'Thursday': 3,
    'Friday': 4,
    'Saturday': 5,
    'Sunday': 6,
}

WEEKS = {
    '1st': 0,
    '2nd': 1,
    '3rd': 2,
    '4th': 3,
}


def meetup_day(year, month, day_of_week, week_of_month):
    """
    Returns a datetime.date object: date(2015, 3, 26)

    Arguments:
        year (int): 2015
        month (int): 3
        day_of_week (string): 'Monday'
        week_of_month (string): Can be from the following:
            1st,
            2nd,
            3rd,
            4th,
            teenth,
            last
    """
    cal = calendar.monthcalendar(year, month)
    weekday = calendar.weekday(year, month, 1)
    # get the ordinal from our dict
    day_of_week = DAYS[day_of_week]

    if week_of_month == 'last':
        # get the last day of the month as ordinal
        day = cal[-1][day_of_week]
        if day == 0:
            day = cal[-2][day_of_week]
    elif week_of_month == 'teenth':
        # get the day as ordinal
        day = 15 + day_of_week - weekday
    else:
        cal_week = WEEKS[week_of_month]
        if weekday > day_of_week:
            cal_week += 1
        day = cal[cal_week][day_of_week]

    return date(year, month, day)

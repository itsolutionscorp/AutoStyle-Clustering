__author__ = 'matt'
from datetime import date


def meetup_day(year, month, day_in_text, search):
    days_in_month = {1: 31, 2: 28, 3: 31, 4: 30, 5: 31, 6: 30, 7: 31, 8: 31, 9: 30, 10: 31, 11: 30, 12: 31}
    week_days = {0: 'Sunday', 1: 'Monday', 2: 'Tuesday', 3: 'Wednesday', 4: 'Thursday', 5: 'Friday', 6: 'Saturday'}

    if is_leap_year(year):
        days_in_month.update({2: 29})

    week_to_search = get_relevant_week_data(month, year, search, days_in_month.get(month))

    for day, week_day_as_int, week_number in week_to_search:
        if week_days.get(week_day_as_int) == day_in_text and week_number == search:
            return date(year, month, day)


def get_day_of_week(month, day, year, calendar_system=1):

    # CalendarSystem = 1 for Gregorian Calendar
    if month < 3:
        month += 12
        year -= 1

    return int((day
                + (2 * month)
                + (6 * (month + 1) / 10)
                + year + (year / 4)
                - (year / 100)
                + (year / 400)
                + calendar_system) % 7)


def get_relevant_week_data(month, year, search, days_in_month):
    week_data = []
    if search == 'teenth':
        for day in range(13, 20):
            week_data.append((day, get_day_of_week(month, day, year), search))
    elif search == 'last':
        #days_in_month[-7:]:
        for day in range(days_in_month-6, days_in_month+1):
            week_data.append((day, get_day_of_week(month, day, year), search))
    elif search == '1st':
        for day in range(1, 8):
            week_data.append((day, get_day_of_week(month, day, year), search))
    elif search == '2nd':
        for day in range(8, 15):
            week_data.append((day, get_day_of_week(month, day, year), search))
    elif search == '3rd':
        for day in range(15, 22):
            week_data.append((day, get_day_of_week(month, day, year), search))
    elif search == '4th':
        for day in range(22, 29):
            week_data.append((day, get_day_of_week(month, day, year), search))

    return week_data


def is_leap_year(year):
    normal_leap = (year % 4 == 0)
    not_leap = (year % 100 == 0)
    not_leap_exception = (year % 400 == 0)

    if normal_leap:
        if not_leap and not not_leap_exception:
            return False
        else:
            return True

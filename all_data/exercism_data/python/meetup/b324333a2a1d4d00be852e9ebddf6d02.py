from calendar import Calendar
from datetime import date
DAY = {
    "Monday": 0, "Tuesday": 1, "Wednesday": 2, "Thursday": 3, "Friday": 4,
    "Saturday": 5, "Sunday": 6
    }
NUMBER = {"1st": 0, "2nd": 1, "3rd": 2, "4th": 3}


def meetup_day(year, month, day, number):
    cal = Calendar().monthdayscalendar(year, month)
    meet_day = DAY[day]
    if number in NUMBER:
        dater = numbered(cal, meet_day, NUMBER[number])
    elif number == 'teenth':
        dater = teens(cal, meet_day)
    else:
        dater = last(cal, meet_day)
    return date(year, month, dater)


def numbered(cal, day, number):
    if cal[0][day] == 0:
        return cal[number+1][day]
    else:
        return cal[number][day]


def teens(cal, day):
    min_date = numbered(cal, day, 0)
    if min_date < 6:
        return min_date + 14
    else:
        return min_date + 7


def last(cal, day):
    if cal[-1][day] == 0:
        return cal[-2][day]
    else:
        return cal[-1][day]

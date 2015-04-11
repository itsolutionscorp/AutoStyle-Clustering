from datetime import date
import calendar

days = {
    "Monday": 0,
    "Tuesday": 1,
    "Wednesday": 2,
    'Thursday': 3,
    "Friday": 4,
    "Saturday": 5,
    "Sunday": 6
}

ordinal = ("1st", "2nd", "3rd", "4th", "last")

calendar_s = calendar.Calendar()


def meetup_day(year, month, day, order):

    calendar_ = calendar_s.monthdayscalendar(year, month)

    list_of_days = []
    for x in range(len(calendar_)):
        list_of_days.append(calendar_[x][days[day]])

    if order == 'teenth':
        meet_day = list_of_days[2]
    elif list_of_days[0] == 0:
        meet_day = list_of_days[ordinal.index(order)+1]
    else:
        meet_day = list_of_days[ordinal.index(order)]

    return date(year, month, meet_day)

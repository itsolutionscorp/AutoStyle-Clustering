from calendar import monthrange
import string
from datetime import date, timedelta


def meetup_day(year: int, month: int, wanted_weekday: string, segment: string) -> date:
    days = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']
    first = date(year, month, 1)
    date_list = [first + timedelta(x) for x in range(0, monthrange(year, month)[1]) if (first + timedelta(x)).weekday() == days.index(wanted_weekday)]
    if segment == 'last':
        return date_list[-1]
    if segment == 'teenth':
        for possible_date in date_list:
            if date(year, month, 13) <= possible_date <= date(year, month, 19):
                return possible_date
    return date_list[['1st', '2nd', '3rd', '4th'].index(segment)]



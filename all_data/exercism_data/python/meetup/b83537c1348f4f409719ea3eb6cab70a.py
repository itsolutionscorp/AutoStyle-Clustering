from calendar import monthrange
import string
from datetime import date, timedelta


def meetup_day(year: int, month: int, wanted_weekday: string, segment: string) -> date:
    days = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']
    first_date = date(year, month, 1)
    date_list = [
        first_date + timedelta(x)
        for x in range(0, monthrange(year, month)[1])
        if (first_date + timedelta(x)).weekday() == days.index(wanted_weekday)
    ]
    if segment == 'last':
        return date_list[-1]
    if segment == 'teenth':
        return [possible_date for possible_date in date_list if 13 <= possible_date.day <= 19][0]
    return date_list[['1st', '2nd', '3rd', '4th'].index(segment)]



from datetime import date
from calendar import monthrange

DESCRIPTORS = {
    '1st': range(1, 8),
    '2nd': range(8, 15),
    '3rd': range(15, 22),
    '4th': range(22, 31)
}

def meetup_day(year, month, day_name, day_descriptor):
    if day_descriptor == 'last':
        last_day = monthrange(year, month)[1]
        return find_day(range(last_day - 6, last_day + 1), year, month, day_name)
    elif day_descriptor == 'teenth':
        return find_day(range(13, 20), year, month, day_name)
    else:
        return find_day(DESCRIPTORS[day_descriptor], year, month, day_name)


def find_day(day_range, year, month, day_name):
    for day in day_range:
        meetup_date = date(year, month, day)
        if meetup_date.strftime("%A") ==  day_name:
            return meetup_date

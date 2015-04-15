from calendar import monthrange
from datetime import date


def meetup_day(year, month, dayname, which):
    which_map = {'1st': 0, '2nd': 1, '3rd': 2, '4th': 3, 'last': -1}

    r = monthrange(year, month)
    full_month = [date(year, month, num) for num in range(1, r[1] + 1)]
    possibles = [day for day in full_month if day.strftime('%A') == dayname]

    if which in which_map:
        return possibles[which_map[which]]
    else:
        teens = [day for day in possibles if day.day in range(12, 20)]
        return teens[0]

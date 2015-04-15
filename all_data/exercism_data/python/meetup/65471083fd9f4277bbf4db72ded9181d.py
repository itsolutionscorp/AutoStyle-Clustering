from datetime import date
from calendar import monthrange

def meetup_day(year, month, weekday, exp):
    days = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']
    meetup_weekday = days.index(weekday)

    first_of_month_date = date(year, month, 1)
    first_of_month_weekday = first_of_month_date.weekday()
    first_of_month_ord = first_of_month_date.toordinal()

    ord_offset = (meetup_weekday - first_of_month_weekday) % 7

    if exp == '2nd':
        ord_offset += 7
    elif exp == '3rd':
        ord_offset += 7 * 2
    elif exp == '4th':
        ord_offset += 7 * 3
    elif exp == 'teenth':
        while ord_offset < 13 - 1:
            ord_offset += 7
    elif exp == 'last':
        last_date = monthrange(year,month)[1]
        while ord_offset < last_date - 1:
            ord_offset += 7

    return date.fromordinal(first_of_month_ord + ord_offset)


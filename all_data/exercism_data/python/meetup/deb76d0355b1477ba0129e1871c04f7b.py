from datetime import date
from calendar import monthrange

# constants
str_weeks_to_int_weeks = {"1st": 0, "2nd": 1, "3rd": 2, "4th": 3, "last": -1}


def day_to_dates(year, month, day, date1, date2):
    dates = []
    # loop through all the dates in the given range
    for i in range(date1, date2):
        date_i = date(year, month, i)
        # if date matches the given day of the week
        if date_i.strftime("%A") == day:
            # add it to the list
            dates.append(i)
    return dates


def meetup_day(year, month, day, week):
    if week == 'teenth':
        dates = day_to_dates(year, month, day, 13, 19)
        result = dates[0]

    else:
        days_in_month = monthrange(year, month)[1] + 1
        dates = day_to_dates(year, month, day, 1, days_in_month)
        # lookup the integer week and use it to get the nth element from list
        result = dates[str_weeks_to_int_weeks[week]]
    
    return date(year, month, result)

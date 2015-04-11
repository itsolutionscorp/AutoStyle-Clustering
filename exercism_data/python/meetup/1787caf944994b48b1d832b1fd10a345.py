""" Exercism """
from calendar import monthrange
import datetime

DAYS = {'Monday': 0,
             'Tuesday': 1,
             'Wednesday': 2,
             'Thursday': 3,
             'Friday': 4,
             'Saturday': 5,
             'Sunday': 6}

def meetup_day(year, month, day_of_week, ending):
    """
    - Calculates the number of days in the month
    - Iterates through the list removing any where the
      day of week does not match the target
    - With the remainder, returns the appropriate item
      based on the 'ending' paramater
    """
    days_in_month = monthrange(year, month)[1]

    possible_days = [datetime.date(year, month, x + 1) for \
        x in range(days_in_month)]

    for day_index in range(days_in_month):
        test_day = datetime.date(year, month, day_index + 1)
        if DAYS[day_of_week] != test_day.weekday():
            possible_days.remove(test_day)

    if ending == '1st':
        return possible_days[0]
    elif ending == '2nd':
        return possible_days[1]
    elif ending == '3rd':
        return possible_days[2]
    elif ending == '4th':
        return possible_days[3]
    elif ending == 'last':
        return possible_days[len(possible_days) - 1]
    elif ending == 'teenth':
        return next((x for x in possible_days if \
            x.day > 12 and x.day < 20), None)

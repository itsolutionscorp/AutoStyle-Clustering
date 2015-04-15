from datetime import date
from dateutil.relativedelta import relativedelta
from calendar import isleap

def meetup_day(year, month, weekday, rank):
    """
    @year: An integer specifying the year.
        Example 2015

    @month: An integer ordinal value.
        Example: 1

    @weekday: Name of a weekday.
        Example: Monday

    @rank: One of the following values:
        1st, 2nd, 3rd, 4th, 5th, last, teenth.
    """

    weekdays = ['Monday', 'Tuesday', 'Wednesday', 'Thursday',
                'Friday', 'Saturday', 'Sunday']

    weekday_ordinal = weekdays.index(weekday)

    start_of_month = date(year, month, 1)
    next_month = start_of_month + relativedelta(months=1)
    days_in_month = (next_month - start_of_month).days

    days = [start_of_month + relativedelta(days=i)
                for i in range(days_in_month)]

    selections = [day for day in days if day.weekday() == weekday_ordinal]

    if rank == '1st':
        return selections[0]
    elif rank == '2nd':
        return selections[1]
    elif rank == '3rd':
        return selections[2]
    elif rank == '4th':
        return selections[3]
    elif rank == '5th':
        return selections[4]
    elif rank == 'last':
        return selections[-1]
    elif rank == 'teenth':
        teenths = [13,14,15,16,17,18,19]
        for selection in selections:
            if selection.day in teenths:
                return selection

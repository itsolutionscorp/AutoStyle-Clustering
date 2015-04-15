import calendar
from datetime import date

day_names = list(calendar.day_name)


def is_teen(n):
    return 13 <= n <= 19


def meetup_day(year, month, weekday, nth):
    # Look up the day name
    weekday_num = day_names.index(weekday)

    # Find out information about the specified month
    first_weekday, days_in_month = calendar.monthrange(year, month)

    # Build a list of matching weekdays
    offset = (weekday_num - first_weekday) % 7
    weekdays = range(offset+1, days_in_month+1, 7)

    if nth == 'last':
        day_of_month = weekdays[-1]
    elif nth == 'teenth':
        day_of_month = filter(is_teen, weekdays)[0]
    else:
        # At this point, assume an ordinal. It'll only ever be 1-5
        day_of_month = weekdays[int(nth[0]) - 1]

    return date(year, month, day_of_month)

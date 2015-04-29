from datetime import date, timedelta
weekdays = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3,
            'Friday': 4, 'Saturday': 5, 'Sunday': 6}
def meetup_day(year, month, weekday, teenth):
    weekday = weekdays[weekday]
    # get the first day of the month
    find_date = date(year, month, 1)
    # get the first correct weekday in the month
    find_date += timedelta(days=(weekday - find_date.weekday())%7)
    try:
        # handle 1st, 2nd, 3rd, 4th
        teenth = int(teenth[0])
        find_date += timedelta(weeks=teenth - 1)
    except ValueError:
        week = timedelta(weeks=1)
        if teenth == 'last':
            next_week = find_date + week
            while next_week.month == find_date.month:
                find_date += week
                next_week += week
        else:
            while find_date.day not in range(13, 20):
                find_date += week

    return find_date

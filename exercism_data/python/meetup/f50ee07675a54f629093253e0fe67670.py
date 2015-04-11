from datetime import date, timedelta
import calendar

def meetup_day(year, month, week_day, occurrence):
    week_days = list(calendar.day_name)
    occurrences = ['1st', '2nd', '3rd', '4th', 'last']
    the_day = date(year, month, 1)

    # day of week
    while the_day.weekday() != week_days.index(week_day):
        the_day += timedelta(days = 1)

    # week in month
    if occurrence == 'teenth':
        while the_day.day < 13:
            the_day += timedelta(weeks = 1)
    else:
        the_day += timedelta(weeks = occurrences.index(occurrence))
        while the_day.month != month: # if overshot
            the_day -= timedelta(weeks = 1)
    return the_day

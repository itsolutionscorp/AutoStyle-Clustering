from datetime import datetime, timedelta

ordinal_suffixes = ('st', 'nd', 'rd', 'th')
weekday_names = ('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday')
week = timedelta(weeks=1)
day = timedelta(days=1)

def meetup_day(year, month, day_name, ordinal):
    ordinal = ordinal.lower()    

    date_string = '{0} {1}'.format(year, month)
    date = datetime.strptime(date_string, '%Y %m')
    target_weekday = weekday_names.index(day_name.title())

    while date.weekday() != target_weekday:
        date += day

    if ordinal == 'teenth':
        while date.day not in range(13, 20):
            date += week

    elif ordinal == 'last':
        while (date + week).month == date.month:
            date += week

    elif ordinal.endswith(ordinal_suffixes):
        occurance = int(ordinal[0]) - 1
        date = date + (week * occurance)

    return date.date()

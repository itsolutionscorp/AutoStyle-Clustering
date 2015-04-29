from datetime import date, timedelta


def meetup_day(year, month, day, week):
    start_date = date(year, month, 1)
    one_day = timedelta(days=1)
    one_week = timedelta(days=7)
    # find the first
    while start_date.strftime('%A') != day:
        start_date += one_day
    if(week == '1st'):
        return start_date
    dates = []
    while start_date.month == month:
        dates.append(start_date)
        start_date += one_week

    if week == '2nd':
        return dates[1]
    if week == '3rd':
        return dates[2]
    if week == '4th':
        return dates[3]
    if week == 'last':
        return dates[-1]

    for d in dates:
        if d.day in range(13, 20):
            return d

from datetime import date
import calendar


days_of_the_week = {
    '1st': range(1, 8),
    '2nd': range(8, 15),
    '3rd': range(15, 22),
    '4th': range(22, 28),
    '5th': range(28, 32),
    'teenth': range(13, 20)
}


def meetup_day(year, month, day, of_the_week):
    dates = calendar.monthcalendar(year, month)
    calendar_day = calendar.__getattribute__(day.upper())
    last_day = calendar.monthrange(year, month)[1]
    days_of_the_week['last'] = range(last_day - 6, last_day + 1)

    for week in dates:
        day_of_the_month = week[calendar_day]
        if day_of_the_month in days_of_the_week[of_the_week]:
            return date(year, month, day_of_the_month)
    raise Exception

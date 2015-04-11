import calendar
from datetime import date


DAYS_OF_THE_WEEK = ['monday', 'tuesday', 'wednesday', 'thursday', 'friday',
                    'saturday', 'sunday']


def meetup_day(year, month, weekday_text, type):
    weekday = get_weekday_index(weekday_text)
    days_of_weekday = [week[weekday] for week in
                       calendar.monthcalendar(year, month) if week[weekday]]
    teenth_day = (set(range(13, 20)) & set(days_of_weekday)).pop()
    pos = {'1st': 0, '2nd': 1, '3rd': 2, '4th': 3, 'last': -1,
           'teenth': days_of_weekday.index(teenth_day)}

    try:
        return date(year, month, days_of_weekday[pos[type]])
    except KeyError:
        raise KeyError('Bad type given')


def get_weekday_index(weekday_text):
    try:
        return DAYS_OF_THE_WEEK.index(weekday_text.lower())
    except ValueError:
        raise ValueError('Bad weekday given')

import calendar
from datetime import date


def meetup_day(year, month, weekday_text, choice):
    weekday = get_weekday_index(weekday_text)
    days_of_weekday = [week[weekday] for week in
                       calendar.monthcalendar(year, month) if week[weekday]]
    teenth_day = (set(range(13, 20)) & set(days_of_weekday)).pop()
    pos = {'1st': 0, '2nd': 1, '3rd': 2, '4th': 3, 'last': -1,
           'teenth': days_of_weekday.index(teenth_day)}

    try:
        return date(year, month, days_of_weekday[pos[choice]])
    except KeyError:
        raise KeyError('Bad choice given')


def get_weekday_index(weekday_text):
    try:
        return list(calendar.day_name).index(weekday_text.capitalize())
    except ValueError:
        raise ValueError('Bad weekday given')
      

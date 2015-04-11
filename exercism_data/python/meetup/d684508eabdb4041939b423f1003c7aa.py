from datetime import date, timedelta

def meetup_day(year, month, day, type):
    day = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'].index(day)
    current_day = date(year, month, 1)
    current_day += timedelta((day - current_day.weekday()) % 7)
    if type != 'teenth' and type != 'last':
        type = int(type[0])
        week = 1
    while True:
        if type == 'teenth':
            if 13 <= current_day.day:
                return current_day
        elif type == 'last':
            if current_day.month != month:
                return current_day - timedelta(7)
        else:
            if week == type:
                return current_day
            week += 1
        current_day += timedelta(7)

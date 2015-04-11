from datetime import date
from calendar import monthrange

def meetup_day(y, m, weekday, qualifier):
    possible_days = {
        '1st': range(1, 8), '2nd': range(8, 15),
        '3rd': range(15, 22), '4th': range(22, 29),
        'teenth': range(13, 20),
        'last': range(monthrange(y, m)[1]-6, monthrange(y, m)[1]+1)
    }
    for d in possible_days[qualifier]:
        if date(y, m, d).strftime('%A') == weekday:
            return date(y, m, d)


if __name__ == '__main__':
    print meetup_day(2013, 5, 'Monday', 'teenth')

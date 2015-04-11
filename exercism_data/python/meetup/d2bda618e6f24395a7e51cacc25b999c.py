import datetime
import time

RECUR = {
    'teenth': 0,
    '1st'   : 1,
    '2nd'   : 2,
    '3rd'   : 3,
    '4th'   : 4,
    'last'  : 5
}

def meetup_day(year, month, day, recur):
    # Make Monday a one. Little easier to work with.
    weekday_num = (time.strptime(day, '%A').tm_wday) + 1

    # Get whatever day of the week the month starts with.
    first_weekday = datetime.date(year, month, 1).weekday()

    if weekday_num <= first_weekday:
        mod = RECUR[recur] * 7
    elif weekday_num > first_weekday:
        mod = (RECUR[recur] - 1) * 7

    offset = weekday_num - first_weekday
    # Stolen from:
    #   http://www.wilsonzhao.com/blog/index.html
    #   Because I have no idea what the hell a 'teenth' is.
    if recur == 'teenth':
        if 14 + offset > 12 and 14 + offset < 20:
            mod = 14

    meetup_day = offset + mod
    return datetime.date(year, month, meetup_day)

if __name__ == '__main__':
    meetup_day(2013, 5, 'Tuesday', '1st')

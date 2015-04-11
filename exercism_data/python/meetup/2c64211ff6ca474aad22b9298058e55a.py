from datetime import timedelta
from datetime import date

weekdays = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']
""" Return meetup date for given input:
    year: desired year
    month: desired month
    day_s: desired day as string
    mod: modifier such as 'teenth', '1st', '2nd', etc.
"""
def meetup_day(year, month, day_s, mod):
    # Call find_date with weekday number Monday = 0 ... Sunday = 6
    # and start date (13 for start of teenth days of the month, and
    # 1, 8, 15, 22 for 1st, 2nd, 3rd, and 4th week respectively. -1
    # to signify 'last'.
    if mod == 'teenth':
        return find_date(year,month,weekdays.index(day_s),13)
    elif mod == '1st':
        return find_date(year,month,weekdays.index(day_s),1)
    elif mod == '2nd':
        return find_date(year,month,weekdays.index(day_s),8)
    elif mod == '3rd':
        return find_date(year,month,weekdays.index(day_s),15)
    elif mod == '4th':
        return find_date(year,month,weekdays.index(day_s),22)
    elif mod == 'last':
        return find_date(year,month,weekdays.index(day_s),-1)
    else:
        'ERROR - invalid modifier!'
        return None

""" Helper function to meetup_day. Return date for given input:
    year: year of input
    month: month of input
    day_n: weekday number
    start: day in month to start search
"""
def find_date(year, month, day_n, start):
    if start == -1:
        # Start on first day of the following month and searching
        # backwards until weekday of meetup == day_n
        temp = date(year,month,1) + timedelta(days=31)
        meetup = date(temp.year,temp.month,1)
        while True:
            meetup -= timedelta(days=1)
            if meetup.weekday() == day_n:
                return meetup
    else:
        # Start on given day and search sequentially until
        # weekday of meetup == day_n
        day = start
        while True:
            meetup = date(year,month,day)
            if meetup.weekday() == day_n:
                return meetup
            else:
                day += 1

from datetime import date, timedelta

def meetup_day(year, month, day, nth ):
    days_in_week = {'Monday': 1, 'Tuesday': 2, 'Wednesday': 3, 'Thursday': 4, 'Friday': 5, 'Saturday': 6, 'Sunday': 7}
    n = {'1st': 1, '2nd': 2, '3rd': 3, '4th': 4}
    which_day = days_in_week[day] # represent with num
    if month == 12:
        last_day = 31
    else:
        last_day = (date(year, month + 1, 1) - timedelta(days=1)).day
    if nth in n:
        count = n[nth]
        for x in range(1, last_day + 1):
            if date(year, month, x).isoweekday() == which_day:
                count -= 1
            if count == 0:
                return date(year, month, x)
    elif nth == 'teenth':  # teenth
        for x in range(13, 20):
            if date(year, month, x).isoweekday() == which_day:
                return date(year, month, x)
    elif nth == 'last':
        for x in range(last_day, 1, -1) :
            if date(year, month, x).isoweekday() == which_day:
                return date(year, month, x)

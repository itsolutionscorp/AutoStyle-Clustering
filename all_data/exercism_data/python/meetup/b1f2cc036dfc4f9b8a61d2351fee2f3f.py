from datetime import date, timedelta

WEEKDAYS = {
    "Monday": 0,
    "Tuesday": 1,
    "Wednesday": 2,
    "Thursday": 3,
    "Friday": 4,
    "Saturday": 5,
    "Sunday": 6
}

ORDINALS = {
    "1st": 1,
    "2nd": 2,
    "3rd": 3,
    "4th": 4
}

def meetup_day(year, month, weekday_name, ordinal):
    d = date(year, month, 1)
    d = find_weekday(d, weekday_name)
    if ordinal == "teenth":
        return find_teenth_weekday(d)
    if ordinal == "last":
        return find_last_weekday(d)
    return find_nth_weekday(d, ORDINALS[ordinal])
        
def find_teenth_weekday(d):
    month = d.month
    while month == d.month:
        if is_teenth(d.day):
            return d
        d += timedelta(days=7)
    return None

def is_teenth(day_of_month):
    return 13 <= day_of_month and day_of_month <= 19

def find_weekday(d, weekday_name):
    while d.weekday() != WEEKDAYS[weekday_name]:
        d += timedelta(days=1)
    return d

def find_nth_weekday(d, n):
    i = 1
    while i < n:
        d += timedelta(days=7)
        i += 1
    return d

def find_last_weekday(d):
    weekday = d.weekday()
    month = d.month
    while month == d.month:
        last_found = d
        d += timedelta(days=7)

    return last_found
        

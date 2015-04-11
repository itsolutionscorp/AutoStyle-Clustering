import datetime

def is_leap_year(year):
    if year % 400 == 0:
        return True
    elif year % 100 == 0:
        return False
    elif year % 4 == 0:
        return True
    return False

def month_days(month, year):
    if month == 1:
        return 31
    elif month == 2 and is_leap_year(year):
        return 29
    elif month == 2:
        return 28
    elif month == 3:
        return 31
    elif month == 4:
        return 30
    elif month == 5:
        return 31
    elif month == 6:
        return 30
    elif month == 7:
        return 31
    elif month == 8:
        return 31
    elif month == 9:
        return 30
    elif month == 10:
        return 31
    elif month == 11:
        return 30
    elif month == 12:
        return 31

def add_gigasecond(birthday):
    days_to_add = 10**9 / 60 / 60 / 24
    day = birthday.day
    month = birthday.month
    year = birthday.year

    while days_to_add > 0:
        if month_days(month, year) == day:
            day = 1
            if month == 12:
                month = 1
                year += 1
            else:
                month += 1
        else:
            day += 1
        days_to_add -= 1

    return datetime.date(year, month, day)

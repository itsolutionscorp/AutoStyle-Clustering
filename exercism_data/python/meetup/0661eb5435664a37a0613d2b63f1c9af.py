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

def meetup_day(year, month, weekday, occurs):
    day = 1
    weekday_int = 0
    if weekday == 'Sunday':
        weekday_int = 6
    elif weekday == 'Monday':
        weekday_int = 0
    elif weekday == 'Tuesday':
        weekday_int = 1
    elif weekday == 'Wednesday':
        weekday_int = 2
    elif weekday == 'Thursday':
        weekday_int = 3
    elif weekday == 'Friday':
        weekday_int = 4
    elif weekday == 'Saturday':
        weekday_int = 5

    while datetime.date(year, month, day).weekday() != weekday_int:
        day += 1

    if occurs == '1st':
        pass
    elif occurs == '2nd':
        day += 7
    elif occurs == '3rd':
        day += 14
    elif occurs == '4th':
        day += 21
    elif occurs == 'last':
        while day <= month_days(month, year):
            day += 7
        day -= 7
    elif occurs == 'teenth':
        while day < 13:
            day += 7

    return datetime.date(year, month, day)

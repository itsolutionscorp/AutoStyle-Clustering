from datetime import date

WEEKDAYS = {'Monday': 0,
            'Tuesday': 1,
            'Wednesday': 2,
            'Thursday': 3,
            'Friday': 4,
            'Saturday': 5,
            'Sunday': 6}

def meetup_day(year, month, weekday, key):
    if key == 'teenth':
        day_range = (13, 19)
    elif key == 'last':
        month_days = get_days_in_month(year, month)
        day_range = (month_days - 6, month_days)
    else:
        num = int(key[0])
        day_range = (num * 7 - 6, num * 7)
    for day in range(day_range[0], day_range[1] + 1):
        test_date = date(year, month, day)
        if test_date.weekday() == WEEKDAYS[weekday]: return test_date

def get_days_in_month(year, month):
    if month == 12:
        next_month_start = date(year + 1, 1, 1)
    else:
        next_month_start = date(year, month + 1, 1)
    return (next_month_start - date (year, month, 1)).days

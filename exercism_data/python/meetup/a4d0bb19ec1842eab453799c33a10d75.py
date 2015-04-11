from datetime import date, timedelta

def meetup_day(year, month, day, ordinal):
    test_date = date(year, month, 1)
    week_multiplier = 0
    if ordinal == 'last':
        test_date = add_month(test_date)
    elif ordinal == 'teenth':
        test_date = date(year, month, 13)
    else:
        if test_date.weekday() <= day_to_int(day):
            week_multiplier = 7 * ordinal_to_int(ordinal)
        else:
            week_multiplier = 7 * (ordinal_to_int(ordinal) + 1)

    return test_date + timedelta(days=week_multiplier + (day_to_int(day) - test_date.weekday()))

def day_to_int(day):
    days = {'Monday': 0,
            'Tuesday': 1,
            'Wednesday': 2,
            'Thursday': 3,
            'Friday': 4,
            'Saturday': 5,
            'Sunday': 6}

    return days[day]

def ordinal_to_int(ordinal):
    ordinals = {'1st': 0,
                '2nd': 1,
                '3rd': 2,
                '4th': 3}

    return ordinals[ordinal]

def add_month(d):
    if d.month == 12:
        return date(d.year + 1, 1, 1)
    else:
        return date(d.year, d.month + 1, 1)

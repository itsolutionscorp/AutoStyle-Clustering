from datetime import date, timedelta

DAYS = [
    'Monday',
    'Tuesday',
    'Wednesday',
    'Thursday',
    'Friday',
    'Saturday',
    'Sunday'
]

MEET = {
    '1st': 1,
    '2nd': 2,
    '3rd': 3,
    '4th': 4,
    'last': -1,
    'teenth': 0
}

TEENTH = range(13, 19)


def delta_days(current_day, target_day):
    days = (target_day - current_day)

    if days <= 0:
        days += 7

    return days


def last_day(date, weekday):
    result = date
    next_date = result + timedelta(days=delta_days(date.weekday(), DAYS.index(weekday)))

    while (next_date.month == date.month):
        result += timedelta(days=delta_days(date.weekday(), DAYS.index(weekday)))
        next_date = result + timedelta(days=delta_days(date.weekday(), DAYS.index(weekday)))

    return (result - date).days


def teenth_day(date, weekday):

    for x in TEENTH:
        temp_date = date.replace(date.year, date.month, x)
        if temp_date.weekday() == DAYS.index(weekday):
            return (temp_date - date).days


def days_to_meetup(date, day_name, meet):
    days = 0
    if date.weekday() != DAYS.index(day_name):
        days = delta_days(date.weekday(), DAYS.index(day_name))

    if MEET[meet] > 1:
        days += ((MEET[meet] - 1) * 7)
    elif MEET[meet] == -1:
        return last_day(date, day_name)
    elif MEET[meet] == 0:
        return teenth_day(date, day_name)

    return days


def meetup_day(year, month, day_name, meet):
    d = date(year, month, 1)

    d = d + timedelta(days=days_to_meetup(d, day_name, meet))

    return d

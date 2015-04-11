from datetime import datetime, timedelta

def meetup_day(year, month, day_of_week, modifier):
    date = datetime(year, month, 1)

    check_constraints = _make_constraint_checker(day_of_week, modifier)
    while not check_constraints(date):
        date += timedelta(days=1)

    return date.date()

def _make_constraint_checker(day_of_week, modifier):
    weekdays = [ 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday' ]

    modifiers = {
        'teenth': lambda date: 13 <= date.day <= 19,
        '1st': lambda date: (date.day - 1) / 7 == 0,
        '2nd': lambda date: (date.day - 1) / 7 == 1,
        '3rd': lambda date: (date.day - 1) / 7 == 2,
        '4th': lambda date: (date.day - 1) / 7 == 3,
        'last': lambda date: (date + timedelta(weeks=1)).month != date.month
    }

    def constraint_checker(date):
        if date.weekday() != weekdays.index(day_of_week): return False
        return modifiers[modifier](date)

    return constraint_checker

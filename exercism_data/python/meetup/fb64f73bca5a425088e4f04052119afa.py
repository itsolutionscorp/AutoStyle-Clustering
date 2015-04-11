from datetime import date
from calendar import monthrange

days_of_week = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']


def get_dates_by_month(year, month):
    """Returns all dates in a given month"""
    _, num_days = monthrange(year, month)
    return [date(year, month, day) for day in range(1, num_days + 1)]


def is_day_of_week(d, day_of_week):
    """Returns true if the date matches the given day_of_week string"""
    return d.weekday() == days_of_week.index(day_of_week)


def select_date_by_specifier(dates, specifier):
    """Chooses the correct date from the list of dates by specifier"""
    specifiers = {
        "1st":    lambda: dates[0],
        "2nd":    lambda: dates[1],
        "3rd":    lambda: dates[2],
        "4th":    lambda: dates[3],
        "last":   lambda: dates[-1],
        "teenth": lambda: [d for d in dates if 13 <= d.day <= 19][0]
    }

    return specifiers[specifier]()


def meetup_day(year, month, day_of_week, specifier):
    dates = [d for d in get_dates_by_month(year, month)
             if is_day_of_week(d, day_of_week)]
    return select_date_by_specifier(dates, specifier)

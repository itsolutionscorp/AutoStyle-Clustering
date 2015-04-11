from datetime import date, timedelta
from calendar import monthrange

DOW_to_ordinal = {dow: ord for (ord, dow) in enumerate([
    'Monday', 'Tuesday', 'Wednesday',
    'Thursday', 'Friday', 'Saturday',
    'Sunday'
])}

def get_months_dates(year, month):
    """Returns all dates in a given month"""
    _, num_days = monthrange(year, month)
    return [date(year, month, day) for day in range(1, num_days + 1)]

def is_day_of_week(d, day_of_week):
    """Returns true if the date matches the given day_of_week string"""
    return d.weekday() == DOW_to_ordinal[day_of_week]

def select_date_by_specifier(dates, specifier):
    """Chooses the correct date from the list of dates by specifier"""
    specifiers = {
        "1st":    lambda dates: dates[0],
        "2nd":    lambda dates: dates[1],
        "3rd":    lambda dates: dates[2],
        "4th":    lambda dates: dates[3],
        "last":   lambda dates: dates[-1],
        "teenth": lambda dates: [d for d in dates if 13 <= d.day < 19][0]
    }

    return specifiers[specifier](dates)

def meetup_day(year, month, day_of_week, specifier): 
    """example meetup_day(2013, 5, 'Monday', 'teenth'))"""
    month_dates = get_months_dates(year, month) 
    dates_by_dow = [d for d in month_dates if is_day_of_week(d, day_of_week)]
    return select_date_by_specifier(dates_by_dow, specifier)

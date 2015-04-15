from calendar import monthrange
from datetime import date


def meetup_day(year, month, day_of_the_week, which):
    """Return a day in the given year and month and on the given
    day of the week that fits the requirements set in 'which'.
    'which' may be one of the following:
        "teenth", "last", "1st", "2nd", "3rd" or "4th"
    """
    month_length = monthrange(year, month)[1]
    days_in_the_month = (date(year, month, day)
                         for day in range(1, month_length + 1))
    candidates = [date_
                  for date_ in days_in_the_month
                  if day_name(date_) == day_of_the_week]

    if which == 'teenth':
        return next(d for d in candidates if 13 <= d.day <= 19)
    if which == 'last':
        return candidates[-1]
    return candidates[int(which[0]) - 1]


def day_name(date_):
    """For a given datetime.date object return the name of the
    day of the week.

    Usage::

      >>> from datetime import date
      >>> day_name(date(2014, 4, 2))
      'Wednesday'

    """
    return date_.strftime('%A')

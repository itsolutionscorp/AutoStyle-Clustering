from calendar import monthrange
from datetime import date


def meetup_day(year, month, day_of_the_week, which):
    """Return a day in the given year and month and on the given
    day of the week that fits the requirements set in 'which'.
    'which' may be one of the following:
        "teenth", "last", "1st", "2nd", "3rd" or "4th"
    """
    days_of_months = [date(year, month, day_of_month) for day_of_month in range(1, monthrange(year, month)[1]+1)]
    meetup_days = [ d for d in days_of_months if day_name(d) == day_of_the_week ]                     

    if which == "last":
        return meetup_days[-1]
    elif which == "teenth":
        return [d for d in meetup_days if d.day >= 13 and d.day <=19 ][0]
    return meetup_days[int(which[:-2]) - 1]


def day_name(date_):
    """For a given datetime.date object return the name of the
    day of the week.
    Usage::
      >>> from datetime import date
      >>> day_name(date(2014, 4, 2))
      'Wednesday'
    """
    return date_.strftime('%A')

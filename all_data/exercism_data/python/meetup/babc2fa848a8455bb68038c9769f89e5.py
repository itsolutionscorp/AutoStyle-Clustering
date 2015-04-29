from calendar import monthrange
from datetime import date


def meetup_day(year, month, weekday, type):
    """
    Return the date in year `year` and month `month` that is a weekday
    `weekday` and is in the week signified by `type`.

    `type` may be
        * "1st", "2nd", "3rd" or "4th" for the 1st week and so on
        * "teenth" for the week from the 13th to the 19th
        * "last" for the last 7 days of the month
    """

    weekdays = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday',
                'Saturday', 'Sunday']

    first_weekday, last_day = monthrange(year, month)

    # type tells us which seven days of the month the requested date must be in
    # this is the first date of these seven days
    first_day_of_week = {'1st': 1,
                         '2nd': 8,
                         'teenth': 13,
                         '3rd': 15,
                         '4th': 22,
                         'last': last_day - 6}[type]

    first_day_of_week_weekday = first_weekday + first_day_of_week - 1

    # how many days between the first of the seven days and the wanted weekday
    delta_days = weekdays.index(weekday) - first_day_of_week_weekday

    # go only forward from the first day
    delta_days %= 7

    return date(year, month, first_day_of_week + delta_days)

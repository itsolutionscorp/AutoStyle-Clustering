import datetime
import calendar


_day_number = {"monday": 0, "tuesday": 1, "wednesday": 2,
               "thursday": 3, "friday": 4, "saturday": 5, "sunday": 6}


def _dayofweek(day_name):
    "Returns number 0-6 corresponding to day of the week Monday-Sunday"
    return _day_number[day_name.lower()]


def meetup_day(year, month, desired_day, desired_week):
    """
    Calculates the date of meetups.

    Typically meetups happen on the same day of the week.
    Examples are:

    - the first Monday
    - the third Tuesday
    - the Wednesteenth (Wednesday in teenth days, 13 to 19)
    - the last Thursday
    """
    ndays = calendar.monthrange(year, month)[1]

    dates = {
        "1st": range(1, 7+1),
        "2nd": range(8, 14+1),
        "3rd": range(15, 21+1),
        "4th": range(22, 28+1),
        "5th": range(29, ndays+1),
        "last": range(ndays, ndays-7-1, -1),
        "teenth": range(13, 19+1)
     }

    desired_weekday = _dayofweek(desired_day)

    for day in dates[desired_week]:
        d = datetime.date(year, month, day)
        if d.weekday() == desired_weekday:
            return d

    raise Exception("could not find valid date")

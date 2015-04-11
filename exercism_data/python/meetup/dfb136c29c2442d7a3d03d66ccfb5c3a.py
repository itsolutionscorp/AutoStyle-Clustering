import datetime
import calendar

_day_number = {"monday": 0, "tuesday": 1, "wednesday": 2,
               "thursday": 3, "friday": 4, "saturday": 5, "sunday": 6}
_week_number = {"1st": 1, "2nd": 2, "3rd": 3, "4th": 4, "5th": 5}


class MeetupDayException(Exception):
    pass


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
    wkday_1, ndays = calendar.monthrange(year, month)

    if desired_week == "teenth":
        start_day = 13
        end_day = 19
    else:
        if desired_week == "last":
            if ndays <= 28:
                start_day = 1+4*7
            else:
                start_day = 1+4*7
        else:
            start_day = 1+7*(_week_number[desired_week]-1)
        end_day = min(start_day+7, ndays)

    desired_weekday = _day_number[desired_day.lower()]

    for day in xrange(start_day, end_day+1):
        d = datetime.date(year, month, day)
        if d.weekday() == desired_weekday:
            return d

    raise MeetupDayException()

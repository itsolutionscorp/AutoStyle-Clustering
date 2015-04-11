from calendar import day_name,monthrange
from datetime import date

def meetup_day(year, month, day, which):
    """Computes what date a meetup will occur.
    year(int) : year of the meetup
    month(int): month of the meetup (1...12)
    day(str)  : day of the week (Monday...Sunday)
    which(str): ordinal of the day in the month

    'which' can be :
    "1st" or "first", "2nd" or "second", "3rd" or "third", "4th" or "fourth"
    Also accepted are "last" and "teenth"
    (teenth is the one that occurs closest after 10th of the month)"""

    # ensure consistency of arguments
    assert type(year) == type(month) == int
    assert type(day) == type(which) == str

    # format strings (dicts come later, as well ensure lookup will be okay.)
    day = day.capitalize()
    if day not in day_name:
        raise ValueError(day+' is not a weekday.')  # meh.
    which = which.lower()

    # iso weekdays (using the calendar module)
    iso_weekdays = {d:list(day_name).index(d)+1 for d in day_name}

    # ordinals (as per indexes in a list) that will be used after filtering
    ords = {"1st":0,"first":0,
            "2nd":1,"second":1,
            "3rd":2,"third":2,
            "4th":3,"fourth":3,
            "last":-1}

    daysinmonth = monthrange(year,month)[1]  # number of days in the month

    days = [date(year,month,day+1)
            for day in range(daysinmonth)]  # 28 to 31 date objects
    # first the list gets "filtered" against the weekday
    days = [d for d in days if d.isoweekday() is iso_weekdays[day]]

    if which in ords:
        # find from ordinal
        return days[ords[which]]
    elif which == "teenth":
        # find the one right after the tenth of the month
        for d in days:
            if d.day >= 10 :
                return d
            # it goes fine because the list is sorted from the beginning
    else:
        raise NotImplementedError("Unknown designation for this meetup.")

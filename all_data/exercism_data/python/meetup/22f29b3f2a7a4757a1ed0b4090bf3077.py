from datetime import date
from calendar import monthrange

DoW = {"Monday":0, "Tuesday":1, "Wednesday":2, "Thursday":3, "Friday":4, "Saturday":5, "Sunday":6}


def meetup_day(year, month, dow, criteria):
    target_day = DoW[dow]

    last_week = monthrange(year, month)[1] - 6

    start_date_dict = {"1st":1, "2nd":8, "3rd":15, "4th":22, "5th":29,
                        "teenth":13, "last":last_week,}

    start_day = start_date_dict[criteria]
    for day in range(start_day, start_day+7):
        if date(year, month, day).weekday() == target_day:
            return date(year,month,day)

    raise IndexError

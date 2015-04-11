from datetime import date
from dateutil.rrule import *


DAYS = {"Monday": MO,
        "Tuesday": TU,
        "Wednesday": WE,
        "Thursday": TH,
        "Friday": FR,
        "Saturday": SA,
        "Sunday": SU}

TYPES = {"1st": 1,
         "2nd": 2,
         "3rd": 3,
         "4th": 4,
         "last": -1}


def meetup_day(year, month, day_of_week, suffix):
    if suffix in TYPES:
        meetup_date = list(rrule(MONTHLY, count=1, bymonth=month, byweekday=DAYS[day_of_week], bysetpos=TYPES[suffix],
                           dtstart=date(year, month, 1)))

        return meetup_date[0].date()

    elif suffix == "teenth":
        meetup_date = list(rrule(MONTHLY, count=1, bymonth=month, byweekday=DAYS[day_of_week],
                                 bymonthday=(13, 14, 15, 16, 17, 18, 19), dtstart=date(year, month, 1)))

        return meetup_date[0].date()

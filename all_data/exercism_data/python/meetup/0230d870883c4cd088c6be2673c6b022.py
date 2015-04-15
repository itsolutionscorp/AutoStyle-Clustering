# -*- coding: utf-8 -*-

import datetime

weekday_to_number = {'Monday' : 0,
                     'Tuesday' : 1,
                     'Wednesday' : 2,
                     'Thursday' : 3,
                     'Friday' : 4,
                     'Saturday' : 5,
                     'Sunday' : 6}

one_week = datetime.timedelta(weeks=1)

def meetup_day(year, month, weekday, descr):
    """
    meetup_day(int, int, str, str) -> date

    Given a years month, return a date with given weekday matching the given
    description. The description has one of the forms:
        "teenth" -> the day of month is in 13 <= x <= 19
        "1st"    -> it is the first date with that weekday of that month
        "2nd"    -> it is the second date with that weekday of that month
        "3rd", "4th" -> see above
        "last"   -> it is the last date with that weekday of that month
    """

    weekday = weekday_to_number[weekday]

    #get the first date with matching weekday in that month
    candidate = datetime.date(year=year, month=month, day=1)
    offset_days = (7 + weekday - candidate.weekday()) % 7
    candidate += datetime.timedelta(days=offset_days)

    if descr == "teenth":
        while candidate.day < 13:
            candidate += one_week
    elif descr == "1st":
        pass
    elif descr == "2nd":
        candidate += one_week
    elif descr == "3rd":
        candidate += one_week * 2
    elif descr == "4th":
        candidate += one_week * 3
    elif descr == "last":
        last, candidate = candidate, candidate + one_week
        while last.month == candidate.month:
            last, candidate = candidate, candidate + one_week
        candidate = last

    return candidate

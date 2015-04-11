# This is sloppy. Nitpick away.

import datetime


def meetup_day(year, month, dotw, ordinal):
    dotw_dict = {"Monday": 0,
                 "Tuesday": 1,
                 "Wednesday": 2,
                 "Thursday": 3,
                 "Friday": 4,
                 "Saturday": 5,
                 "Sunday": 6}
    dotw_num = dotw_dict[dotw]
    first = datetime.date(year, month, 1)
    dotw_first = first.weekday()
    dotw_diff = dotw_num - dotw_first
    if dotw_diff == 0:
        first_day = first
    if dotw_diff > 0:
        first_day = first + datetime.timedelta(days=dotw_diff)
    if dotw_diff < 0:
        first_day = first + datetime.timedelta(days=(7 + dotw_diff))
    target_date = first_day
    if ordinal[0].isdigit() and ordinal[0] != "1":
        offset = 7 * (int(ordinal[0])-1)
        target_date = first_day + datetime.timedelta(days=offset)
    if ordinal == "teenth":
        if 12 < meetup_day(year, month, dotw, "2nd").day < 20:
            target_date = meetup_day(year, month, dotw, "2nd")
        else:
            target_date = meetup_day(year, month, dotw, "3rd")
    if ordinal == "last":
        this_month = datetime.date(year=year, month=month, day=28)
        next_month = this_month + datetime.timedelta(days=5)
        next_first = meetup_day(next_month.year, next_month.month, dotw, "1st")
        target_date = next_first + datetime.timedelta(days=-7)
    if target_date.month != month:
        raise Exception
    return target_date

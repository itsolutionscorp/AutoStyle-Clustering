from calendar import monthrange
from datetime import date


def meetup_day(year, month, day_o_week, which_day):

    days_in_month = monthrange(year, month)[1] + 1
    # get the dates for valid days of the month(one's that are the correct
    # weekday)
    possible_days = [date(year, month, day)
                     for day in range(1, days_in_month) if date(year, month, day).strftime('%A') == day_o_week]

    if which_day == 'teenth':

        for _date in possible_days:
            if(13 <= _date.day <= 19):
                res = _date

    elif which_day == 'last':
        res = possible_days[-1]

    else:
        res = possible_days[int(which_day[0]) - 1]

    return res

from datetime import date, timedelta

def meetup_day(year, month, day, flag):
    days = {
        'Monday': 0,
        'Tuesday': 1,
        'Wednesday': 2,
        'Thursday': 3,
        'Friday': 4,
        'Saturday': 5,
        'Sunday': 6
    }

    ## positional flags ##
    # 1st - first day, etc.

    def pos_day(flag):
        d = date(year, month, 1)
        days_count = 0
        while True:
            if d.weekday() == days[day]:
                days_count += 1
                if days_count == flag:
                    return d
            d += timedelta(days=1)

    pos_flags = {
        '1st': 1,
        '2nd': 2,
        '3rd': 3,
        '4th': 4
    }

    if flag in pos_flags:
        return pos_day(pos_flags[flag])

    ## last day of month ##

    def last_day():
        d = date(year, month+1, 1)
        while True:
            if d.weekday() == days[day] and d.month == month:
                return d
            d -= timedelta(days=1)

    if flag == "last":
        return last_day()

    ## teenth day ##

    def teenth_day():
        d = date(year, month, 13)
        while 13 <= d.day <= 19:
            if d.weekday() == days[day]:
                return d
            d += timedelta(days=1)

    if flag == "teenth":
        return teenth_day()

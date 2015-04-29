from datetime import date


def meetup_day(year, month, week_day, type):

    if type == 'teenth':
        return week_day_teenth(year, month, week_day)

    raise ValueError("No such day!")

def week_day_teenth(year, month, week_day):

    for day in range(13, 20):
        dt = date(year, month, day)
        if (dt.strftime("%A") == week_day):
            return dt

    raise ValueError("No such day!")

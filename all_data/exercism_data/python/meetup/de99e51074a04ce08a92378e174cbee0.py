from datetime import date

def meetup_day(year, month, week_day, type):

    for day in range(13, 20):
        dt = date(year, month, day)
        if (dt.strftime("%A") == week_day):
            return dt

    raise ValueError("No such day!")

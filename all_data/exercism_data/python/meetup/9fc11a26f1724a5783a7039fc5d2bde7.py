from datetime import datetime as date


def meetup_day(year, month, dow, qualifier):
    first_date = date(year, month, 1)
    first_day = first_day_of_week(first_date, dow)
    month_end = end_of_month(first_date)
    days = days_in_month(first_day, month_end)
    if qualifier[0].isdigit():
        meetup_date = days[int(qualifier[0])-1]
    elif qualifier == "teenth":
        meetup_date = filter(lambda x: 13 <= x.day <= 19, days)[0]
    elif qualifier == "last":
        meetup_date = days[-1]
    return meetup_date.date()


def days_in_month(first_day, end_of_month):
    day_of_month = first_day.day
    return [first_day.replace(day=day_of_month + i * 7) for i in xrange(
        (end_of_month - day_of_month) / 7 + 1)]


def first_day_of_week(first_date, dow):
    for day in xrange(1, 8):
        first_date = first_date.replace(day=day)
        if first_date.strftime("%A") == dow:
            return first_date


def end_of_month(first_date):
    end_of_month = first_date.replace(day=27)
    days = [(end_of_month + (
        first_date.replace(day=i) - first_date)).day for i in xrange(2, 6)]
    return max(days)

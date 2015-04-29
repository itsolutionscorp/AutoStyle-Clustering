import datetime


def add_gigasecond(date):
    new_datetime = datetime.datetime(date.year, date.month, date.day)
    new_date = new_datetime + datetime.timedelta(seconds=10**9)
    return new_date.date()

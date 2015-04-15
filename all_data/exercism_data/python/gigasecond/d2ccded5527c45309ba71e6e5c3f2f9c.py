from datetime import date, timedelta


def add_gigasecond(a_date):
    return a_date + timedelta(seconds=10**9)

import datetime


def add_gigasecond(dte):
    return dte + datetime.timedelta(seconds=10**9)

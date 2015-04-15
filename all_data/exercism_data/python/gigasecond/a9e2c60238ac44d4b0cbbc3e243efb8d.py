# 10 ** 9

from datetime import date, timedelta

def add_gigasecond(original_date):

    one_gigasecond = timedelta(seconds=10**9)

    return original_date + one_gigasecond

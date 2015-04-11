import datetime


def add_gigasecond(input_date):
    time_delta = datetime.timedelta(0, 10**9)
    return input_date + time_delta

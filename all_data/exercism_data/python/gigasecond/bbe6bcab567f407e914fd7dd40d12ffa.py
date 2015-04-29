import datetime

def add_gigasecond(initial_date):
    return initial_date + datetime.timedelta(seconds=1_000_000_000)

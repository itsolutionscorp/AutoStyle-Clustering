import datetime

def add_gigasecond(input_date):
    giga_seconds = datetime.timedelta(seconds=10**9)
    return input_date + giga_seconds

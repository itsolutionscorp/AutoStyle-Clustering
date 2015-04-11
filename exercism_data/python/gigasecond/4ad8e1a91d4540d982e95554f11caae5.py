import datetime

def add_gigasecond(birthday):
    delta = datetime.timedelta(seconds=1000000000)
    result = birthday + delta
    return result

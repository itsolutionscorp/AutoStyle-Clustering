import datetime as dt

def add_gigasecond(d, _gigasecond=dt.timedelta(0, 1000000000)):
    return d + _gigasecond

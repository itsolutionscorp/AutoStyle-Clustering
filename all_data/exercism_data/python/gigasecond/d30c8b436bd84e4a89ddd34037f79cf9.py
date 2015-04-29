from datetime import timedelta

def add_gigasecond(d):
    gigasecond = timedelta(days=((10 ** 9) / 60 / 60 / 24))
    return d + gigasecond

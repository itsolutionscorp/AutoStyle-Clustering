from datetime import date, timedelta

def add_gigasecond(time):
    gigasecond = timedelta(seconds = 10 ** 9)
    return time + gigasecond

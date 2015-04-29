import datetime

def add_gigasecond(what):
    return what+datetime.timedelta(seconds=pow(10,9))

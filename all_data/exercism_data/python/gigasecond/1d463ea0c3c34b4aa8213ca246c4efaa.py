import datetime


def add_gigasecond(date):
    gigasecond = datetime.timedelta(seconds=1e9)
    
    return date + gigasecond

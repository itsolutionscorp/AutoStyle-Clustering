import datetime

gigasecond = datetime.timedelta(seconds=10**9)

def add_gigasecond(date):
    return date + gigasecond

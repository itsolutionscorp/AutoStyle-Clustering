from datetime import timedelta

def add_gigasecond(date):

    gigadate = date + timedelta(seconds=1e9)

    return gigadate

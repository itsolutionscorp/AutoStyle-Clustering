import datetime

def add_gigasecond(bday):

    gigadate = bday + datetime.timedelta(0,10**9)

    return gigadate

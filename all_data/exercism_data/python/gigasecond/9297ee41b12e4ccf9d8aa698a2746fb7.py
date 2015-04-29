from datetime import date

import time

def add_gigasecond(date):

    gigadate = time.mktime(date.timetuple()) + (10**9)
    newgigadate = date.fromtimestamp(gigadate)

    return newgigadate

##Not sure why this isn't passing
##It returns the same values, but I guess as strings?

import datetime, time

def add_gigasecond(date):

    gigadate = time.mktime(date.timetuple())
    gigadate += 1000000000
    gigadate = time.localtime(gigadate)
    gigadate = int(gigadate.tm_year), int(gigadate.tm_mon), int(gigadate.tm_mday)

    return gigadate

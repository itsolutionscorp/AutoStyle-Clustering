import datetime
import time


def add_gigasecond(date):
    unixTime = time.mktime(date.timetuple())
    addedTime = unixTime + 10**9
    return datetime.datetime.fromtimestamp(addedTime)

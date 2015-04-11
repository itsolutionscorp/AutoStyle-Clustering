import datetime
import time

def add_gigasecond(date):
    timestamp1 = datetime.datetime(year=date.year, month=date.month, day=date.day)
    timestamp1 = time.mktime(timestamp1.timetuple())
    timestamp2 = timestamp1 + 10 ** 9
    result = datetime.datetime.fromtimestamp(timestamp2).strftime('%Y-%m-%d')
    return result

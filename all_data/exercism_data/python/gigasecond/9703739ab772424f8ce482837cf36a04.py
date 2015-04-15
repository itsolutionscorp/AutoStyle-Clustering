import datetime
import time


def add_gigasecond(start):
    start_dt = datetime.datetime(start.year, start.month, start.day)
    start_ts = time.mktime(start_dt.timetuple())
    return datetime.date.fromtimestamp(start_ts + 1000000000)

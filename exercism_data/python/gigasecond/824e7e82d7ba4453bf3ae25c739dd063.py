import datetime
import time


def add_gigasecond(start):
    start_ts = time.mktime((start.year, start.month, start.day, 0, 0, 0, 0, 0, 0))
    return datetime.date.fromtimestamp(start_ts + 1000000000)

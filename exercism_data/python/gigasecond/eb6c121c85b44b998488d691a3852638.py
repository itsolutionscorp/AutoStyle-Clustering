'''
adds 10^9 seconds (1 gigasecond) to the date "day"
'''
from datetime import timedelta


def add_gigasecond(day):
    return day + timedelta(seconds=1e9)
#gigasecond.py
#The future is yours!

import datetime


def add_gigasecond(birthday):
    try:
        return birthday + datetime.timedelta(seconds=10**9)
    except TypeError:
        print "Error: Invalid input date"

#!/usr/bin/python

import time
from datetime import date

def add_gigasecond(tstamp):
    to_add = time.mktime(tstamp.timetuple())
    return date.fromtimestamp(to_add + 1000 ** 3)

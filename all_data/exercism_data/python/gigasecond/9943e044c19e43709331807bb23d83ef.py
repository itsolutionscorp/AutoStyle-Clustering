from __future__ import division
from datetime import date, timedelta

class Gigasecond(object):
    delta = timedelta(0, 1000000000)
    def __init__(self, day):
        self.day = day
        self.date = day + self.delta

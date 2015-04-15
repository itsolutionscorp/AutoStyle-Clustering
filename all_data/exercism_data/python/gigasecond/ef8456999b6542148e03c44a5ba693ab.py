#!/usr/bin/env python

from datetime import date, timedelta

SECONDS_PER_DAY = 60 * 60 * 24

class Gigasecond:
    def __init__(self, birthday):
        self.date = birthday + timedelta(days = 1e9 / SECONDS_PER_DAY)

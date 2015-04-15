__author__ = 'js'

from datetime import date, timedelta

def add_gigasecond(birthday):
    # adds 11,574 (~1 Gs) days to birthday
    return birthday + timedelta(days=11574)

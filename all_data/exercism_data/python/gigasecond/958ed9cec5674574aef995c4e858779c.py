__author__ = "Blackfry"

from datetime import timedelta


def add_gigasecond(a_date):
    return a_date + timedelta(seconds=1e9)

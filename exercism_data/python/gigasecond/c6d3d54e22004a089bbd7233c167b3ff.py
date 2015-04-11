__author__ = 'Cedric Zhuang'

from datetime import timedelta


def add_gigasecond(start):
    return start + timedelta(10**9 / 60 / 60 / 24)


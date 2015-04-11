from datetime import timedelta
from math import pow


def add_gigasecond(date):
    return date + timedelta(0, pow(10, 9))

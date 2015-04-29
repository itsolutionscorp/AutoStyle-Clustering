from datetime import timedelta
import math

def add_gigasecond(birthday):
    """Calculate the date of the gigasecond anniversary given a date"""
    return birthday + timedelta(seconds=math.pow(10,9))

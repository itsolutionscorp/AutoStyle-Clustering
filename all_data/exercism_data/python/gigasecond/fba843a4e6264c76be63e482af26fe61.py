"""
Gigasecond package
"""
from datetime import timedelta
import math

def add_gigasecond(birth):
    """
    Add gigasecond
    """
    return birth + timedelta(seconds=math.pow(10, 9))
# add_gigasecond()

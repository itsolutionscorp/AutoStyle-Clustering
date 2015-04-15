from datetime import *


import string
__author__ = "Bill Davis"

def add_gigasecond(now):
    gigasecond = 10**9
    return now + timedelta(seconds = gigasecond)

__author__ = 'DIA'

import math
import time


def on_square(num):
    return math.pow(2, num - 1)


##
# Measuring time:
# http://stackoverflow.com/questions/5478351/python-time-measure-function
##
def timing(f):
    def wrap(*args):
        time1 = time.time()
        ret = f(*args)
        time2 = time.time()
        print('[%s] function took %0.3f ms' % (f.__name__, (time2 - time1) * 1000.0))
        return ret

    return wrap


@timing
def total_after(num):
    total = 0
    while num > 0:
        total += on_square(num)
        num -= 1
    return total

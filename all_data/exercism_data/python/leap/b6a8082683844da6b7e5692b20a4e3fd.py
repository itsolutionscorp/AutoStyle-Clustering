__author__ = 'tzortzis'


def is_leap_year(c):


    if (c - 2000) % 4 == 0 and c % 100 != 0:
        c = True
    elif c % 400 == 0:
        c = True
    else:
        c = False

    return c

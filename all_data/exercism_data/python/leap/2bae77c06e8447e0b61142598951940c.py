#!/usr/bin/env python

def is_leap_year(year):
    year = int(year)
    if (year % 400) == 0:
        return True
    if (year % 100) == 0:
        return False
    if (year % 4) == 0:
        return True
    return False

if __name__ == '__main__':
    import sys

    if len(sys.argv) != 2:
        print "Usage is ./year.py year"
        sys.exit()

    print is_leap_year(sys.argv[1])

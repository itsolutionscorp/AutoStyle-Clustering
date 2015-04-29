#!/usr/bin/python

def is_leap_year(year):

    a = bool(year % 4)
    b = bool(year % 100)
    c = bool(year % 400)

    return (not a and b or not c)

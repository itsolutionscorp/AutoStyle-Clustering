#!/usr/bin/python

def isLeapYear(year):
    def isDivisible(denominator):
        return not year % denominator
    return isDivisible(4) and (not isDivisible(100) or isDivisible(400))

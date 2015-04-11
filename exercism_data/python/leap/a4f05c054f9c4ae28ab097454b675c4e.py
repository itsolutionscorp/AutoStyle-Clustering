#!/usr/bin/env python

def is_leap_year(y):

     return ( y % 400 == 0 ) or ( y % 4 == 0 ) and not ( y % 100 == 0 )

# not, and, or

#!/usr/bin/env python
# -*- coding: utf-8 -*-

from sys import exit


# class Year(object):
#     def __init__(self, year=None):
#         if year is None:
#             print "You didn't type a year!"
#             exit(0)
#         if type(year) != int:
#             print "Please use numeric interpretation of numbers!"
#             exit(0)
#         self.year = year

#     def is_leap_year(self):
#         if (self.year % 4 == 0 and self.year % 400 == 0
#                 and self.year % 100 != 0):
#             return True
#         else:
#             return False


def is_leap_year(year):
    """returns True:
    on every year that is evenly divisible by 4
      except every year that is evenly divisible by 100
        unless the year is also evenly divisible by 400"""
    if year is None:
        print "You didn't type a year!"
        exit(1)
    if type(year) != int:
        print "Please use numeric interpretation of numbers!"
        exit(1)
    if not year % 4 == 0 or \
            (year % 100 == 0 and not year % 400 == 0):
        return False
    # prefer to use else with if to show relation
    else:
        return True

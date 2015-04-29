#!/usr/bin/env python
# -*- coding: utf-8 -*-

# from sys import exit


def is_leap_year(year):
    return year % 4 == 0 and (year % 400 == 0 or year % 100 != 0)


# class Year(object):
#     def __init__(self, year=None):
#         if year is None:
#             print "You didn't type a year!"
#             exit(0)
#         if type(year) != int:
#             print "Please use numeric interpretation of numbers!"
#             exit(0)
#         self.year = year

#     def is_divisible_by(self, num):
#         return self.year % num == 0

#     def is_leap_year(self):
#         return (is_divisible_by(4) and is_divisible_by(400)
#                 or not is_divisible_by(100))

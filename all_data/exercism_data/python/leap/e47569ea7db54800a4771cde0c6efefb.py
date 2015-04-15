#!/usr/bin/python2


def is_leap_year(year):
    '''Here you can simply use precedence rules and short-circuiting'''
    return not year % 4 and year % 100 != 0 or year % 400 == 0

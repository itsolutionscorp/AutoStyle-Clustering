#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""
.. moduleauthor:: Timothy Helton

"""

__version__ = '0.0.2'
script_name = 'Leap Year'
title = '{}: version {}'.format(script_name, __version__)


def even_div(dividend, divider):
    """
    Function will determine if the quotient of the dividend and the divider
    is even or not.

    :return: True if division of the dividend by the divider has no remainder
    :rtype: bool
    """
    return not bool(dividend % divider)


def is_leap_year(year):
    """
    Function will determine if the input is a leap year.

    :param int year: integer of year to be evaluated
    :return: True if leap year and False if not
    :rtype: bool
    """
    return(even_div(year, 4) and
           not even_div(year, 100) or even_div(year, 400))


if __name__ == '__main__':
    is_leap_year(2000)

    profile_statement = 'is_leap_year(1900)'
    timing = False
    benchmarking, repeat, number = False, 3, 1E6
    setup = 'from __main__ import is_leap_year'

    if timing:
        import cProfile
        import subprocess

        filename = '{0}.profile'.format(__file__.rstrip('.py'))
        cProfile.run(profile_statement, filename=filename)
        subprocess.call(['snakeviz', filename])

    if benchmarking:
        import timeit

        t_b = timeit.Timer(stmt=profile_statement, setup=setup)
        r_b = int(repeat)
        n_b = int(number)
        timing_info = t_b.repeat(repeat=r_b, number=n_b)
        timing_result = [x_b / n_b for x_b in timing_info]

        print('Average Execution Time: {0}'.format(min(timing_result)))

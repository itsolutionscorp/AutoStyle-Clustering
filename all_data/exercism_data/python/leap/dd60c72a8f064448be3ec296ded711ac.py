#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""
.. moduleauthor:: Timothy Helton

"""

__version__ = '0.0.1'
script_name = 'Leap Year'
title = '{}: version {}'.format(script_name, __version__)


def is_leap_year(year):
    """
    Function will determine if the input is a leap year.

    :param int year: integer of year to be evaluated
    :return: True if leap year and False if not
    :rtype: bool
    """
    divisible = []
    for denominator in [4, 100, 400]:
        divisible.append(not bool(year % denominator))

    if all(divisible):
        leap_year = True
    elif divisible[0] and not divisible[1]:
        leap_year = True
    else:
        leap_year = False

    return leap_year


if __name__ == '__main__':
    is_leap_year(1900)

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

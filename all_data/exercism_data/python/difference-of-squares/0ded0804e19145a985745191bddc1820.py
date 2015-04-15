#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
.. moduleauthor:: Timothy Helton <timothy.j.helton@gmail.com>

"""

__version__ = '0.0.1'
script_name = 'Difference of Squares'
title = '{}: version {}'.format(script_name, __version__)


def sum_of_squares(n):
    """
    Calculate the sum of the squares for the first n natural numbers.

    :param int n: number of natural numbers to be operated on
    :return: sum of the square for 1 to n
    :rtype: int
    """
    return sum([x**2 for x in range(1, n+1)])


def square_of_sum(n):
    """
    Calculate the square of the sum for the first n natural numbers.

    :param int n: number of natural numbers to be operated on
    :return: square of the sum of 1 to n
    :rtype: int
    """
    return sum(range(1, n+1))**2


def difference(n):
    """
    Calculate the difference between the square of sums and the sum of squares.

    :param int n: number of natural numbers to be operated on
    :return: difference between square of sums and sum of squares
    :rtype: int
    """
    return square_of_sum(n) - sum_of_squares(n)


if __name__ == '__main__':
    print(square_of_sum(5))

    profile_statement = ''
    timing = False
    benchmarking, repeat, number = False, 3, 1E6
    setup = None
    # setup = '\n'.join(('from __main__ import <EnterFuncVarOrClassHere>',
    # 'from __main__ import <EnterFuncVarOrClassHere>'))

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

        notify.center_output(
            'Average Execution Time: {0}'.format(min(timing_result)))

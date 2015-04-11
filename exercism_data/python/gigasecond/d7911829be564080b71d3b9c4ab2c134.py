#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
.. moduleauthor:: Timothy Helton

"""

import datetime


__version__ = '0.0.1'
script_name = 'Gigasecond Anniversary'
title = '{}: version {}'.format(script_name, __version__)


def add_gigasecond(initial_date):
    """
    Function will determine date 1E9 seconds in the future from given date.

    :param <datetime_object> initial_date: datetime object for the initial date
    :return: date 1E9 seconds ahead of initial date
    :rtype: <datetime_object>
    """
    return initial_date + datetime.timedelta(seconds=1E9)


if __name__ == '__main__':
    print(add_gigasecond(datetime.date(2011, 4, 25)))

    profile_statement = 'add_gigasecond(datetime.date(2011, 4, 25))'
    timing = False
    benchmarking, repeat, number = False, 3, 1E6
    setup = None
    # setup = '\n'.join(('from __main__ import <EnterFuncVarOrClassHere>',
    #                    'from __main__ import <EnterFuncVarOrClassHere>'))

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

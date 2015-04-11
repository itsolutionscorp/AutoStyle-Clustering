#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
.. moduleauthor:: Timothy Helton

"""

import calendar
import datetime

__version__ = '0.0.1'
script_name = 'Meetup'
title = '{}: version {}'.format(script_name, __version__)


def meetup_day(year, month, day_of_week, option):
    """
    Function will determine the date of meetup.

    :param int year: year of meetup
    :param int month: month of meetup
    :param str day_of_week: name of meetup day
    :param str option: modifier for choosing meetup (1st, 2nd, 3rd, 4th, \
        first, last, teenth)
    :return: date of meetup
    :rtype: datetime object
    """
    week = ['mon', 'tue', 'wed', 'thu', 'fri', 'sat', 'sun']
    teens = range(13, 20)
    target_day = [week.index(day) for day in week
                  if day_of_week.lower().startswith(day)].pop()

    meetup = []
    for (day, weekday) in calendar.Calendar().itermonthdays2(year, month):
        if weekday == target_day and day != 0:
            meetup.append(day)

    meet_day = {'1st': meetup[0], '2nd': meetup[1], '3rd': meetup[2],
                '4th': meetup[3], 'last': meetup[-1], 'first': meetup[0],
                'teenth': [x for x in meetup if x in teens].pop()}

    return datetime.date(year, month, meet_day[option])


if __name__ == '__main__':
    print(meetup_day(2013, 5, 'Monday', 'teenth'))

    profile_statement = ''
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

#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""
.. moduleauthor:: Timothy Helton

"""

__version__ = '0.0.1'
script_name = 'Hamming Distance Calculator'
title = '{}: version {}'.format(script_name, __version__)


def distance(strand_1, strand_2):
    """
    Function will calculate the hamming distance between the two given DNA
    strands.

    :param str strand_1: string of DNA
    :param str strand_2: string of DNA
    :return: hamming distance
    :rtype: int
    """
    return sum([1 for x in zip(strand_1, strand_2) if len(set(x)) == 2])

if __name__ == '__main__':
    print(distance('A', 'A'))

    profile_statement = 'distance("A", "A")'
    timing = True
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

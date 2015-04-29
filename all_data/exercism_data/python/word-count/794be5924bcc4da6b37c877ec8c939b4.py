#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""
.. moduleauthor:: Timothy Helton

"""

__version__ = '0.0.1'
script_name = 'Word Count'
title = '{}: version {}'.format(script_name, __version__)


def word_count(phrase):
    """
    Display the words in a phrase and number of times each word is used.

    :param str phrase: string of words
    :return: dictionary of words and the number of times they occur in phrase
    :rtype: dict
    """
    words = phrase.split()
    count = {x: 0 for x in set(words)}

    for word in words:
        count[word] += 1

    return count


if __name__ == '__main__':
    print(word_count('word test word'))

    profile_statement = ''
    timing = False
    benchmarking, repeat, number = False, 3, 1E6
    setup = None
    # setup = '\n'.join(('from __main__ import <EnterFuncVarOrClassHere>',
    #                      'from __main__ import <EnterFuncVarOrClassHere>'))

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

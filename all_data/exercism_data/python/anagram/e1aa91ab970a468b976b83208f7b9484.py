#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""
.. moduleauthor:: Timothy Helton <timothy.j.helton@gmail.com>

"""

from collections import Counter


def detect_anagrams(word, candidates):
    """
    Determine if anagram of word exists in the given list of candidates.

    :param str word: word to be tested
    :param list candidates: potential list of anagrams
    :return: item from candidates that is an anagram of word if one exists
    :rtype: list
    """
    letters = Counter(word.lower())

    return [x for x in candidates
            if x.lower() != word.lower() and Counter(x.lower()) == letters]


if __name__ == '__main__':
    print(detect_anagrams('banana', ['banana']))

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

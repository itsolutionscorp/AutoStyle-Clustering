#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
.. moduleauthor:: Timothy Helton

"""

__version__ = '0.0.1'
script_name = 'Exercism Bob Problem'


def hey(what):
    """
    Function will provide Bob's response to a given statement.

    :param str what: question asked of Bob the lackadaisical teenager
    :return: string of Bob's response
    :rtype: str
    """
    reply = {'silent': 'Fine. Be that way!',
             'yell': 'Whoa, chill out!',
             'question': 'Sure.',
             'remainder': 'Whatever.'}

    what = what.strip()

    if what == '':
        response = reply['silent']
    elif all([what.upper() == what,
              not what.rstrip('?').isnumeric(),
              not what.replace(', ', '').isdigit()]):
        response = reply['yell']
    elif what.endswith('?'):
        response = reply['question']
    else:
        response = reply['remainder']

    return response

if __name__ == '__main__':
    test = '4?'
    print(hey(test))

    statement = ''
    timing = False
    benchmarking, repeat, number = False, 3, 1E6
    setup = None
    # setup = '\n'.join(('from __main__ import <EnterFuncVarOrClassHere>',
    # 'from __main__ import <EnterFuncVarOrClassHere>'))

    if timing:
        import cProfile
        import subprocess

        filename = '{0}.profile'.format(__name__)
        cProfile.run(statement, filename=filename)
        subprocess.call(['snakeviz', filename])

    if benchmarking:
        import timeit

        t_b = timeit.Timer(stmt=statement, setup=setup)
        r_b = repeat
        n_b = number
        timing_info = t_b.repeat(repeat=r_b, number=n_b)

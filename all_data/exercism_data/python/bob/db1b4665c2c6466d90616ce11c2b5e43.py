#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
.. moduleauthor:: Timothy Helton

"""

__version__ = '0.0.2'
script_name = 'Exercism Bob Problem'
title = '{}: version {}'.format(script_name, __version__)


class Bob():
    """
    Class to provide Bob's response to a given statement.

    :attributes:

        * **statement**: original query posed to Bob

        * **response**: Bob's response to the query

    :staticmethods:

        * **hey**:

        * **silent**: response to *silent* query

        * **yell**: response to *yell* query

        * **question**: response to *question* query

        * **remainder**: response to *all other* queries
    """
    def __init__(self, statement):
        self._statement = None
        self.statement = statement
        self.response = self.hey()

    def __str__(self):
        return((80 * '*' + '\n{}\n\n' +
                '{:14} "{}"\n' +
                "Bob's " + 'Response: "{}"\n').format(title.center(80),
                                                      'Statement:',
                                                      self.statement,
                                                      self.response))

    @property
    def statement(self):
        return self._statement

    @statement.setter
    def statement(self, value):
        self._statement = value.strip()

    def hey(self):
        """
        Function will provide Bob's response to a given statement.
        """
        stmt = self.statement

        if stmt == '':
            response = self.silent()
        elif all([stmt.upper() == stmt,
                  not stmt.rstrip('?').isnumeric(),
                  not stmt.replace(', ', '').isdigit()]):
            response = self.yell()
        elif stmt.endswith('?'):
            response = self.question()
        else:
            response = self.remainder()

        return response

    @staticmethod
    def silent():
        """
        Bob's reply to a silent query.
        """
        return 'Fine. Be that way!'

    @staticmethod
    def yell():
        """
        Bob's reply to a yell query.
        """
        return 'Whoa, chill out!'

    @staticmethod
    def question():
        """
        Bob's reply to a question query.
        """
        return 'Sure.'

    @staticmethod
    def remainder():
        """
        Bob's reply to all remaining queries.
        """
        return 'Whatever.'


if __name__ == '__main__':
    test = '4?    '
    print(Bob(test))

    profile_statement = ''
    timing = False
    benchmarking, repeat, number = False, 3, 1E6
    setup = None
    # setup = '\n'.join(('from __main__ import <EnterFuncVarOrClassHere>',
    # 'from __main__ import <EnterFuncVarOrClassHere>'))

    if timing:
        import cProfile
        import subprocess

        filename = '{0}.profile'.format(__name__)
        cProfile.run(profile_statement, filename=filename)
        subprocess.call(['snakeviz', filename])

    if benchmarking:
        import timeit

        t_b = timeit.Timer(stmt=profile_statement, setup=setup)
        r_b = repeat
        n_b = number
        timing_info = t_b.repeat(repeat=r_b, number=n_b)

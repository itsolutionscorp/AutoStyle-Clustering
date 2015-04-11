#!/usr/bin/env/python
# -*- coding: utf-8 -*-
'''
First Python exercise for exercism

Use:
    >>> import bob

    >>> from bob import hey

    >>> hey("Howdy")
    'Whatever.'

    >>> hey("What time is it?")
    'Sure.'

    >>> hey("Look Out!")
    'Whatever.'

    >>> hey("WE ARE THE KNIGHTS WHO SAY 'NI!'")
    'Whoa, chill out!'

    >>> hey("42?")
    'Sure.'

    >>> hey('')
    'Fine. Be that way!'

Goal:
    Bob is a lackadaisical teenager.
    In conversation, his responses are very limited.

    Bob answers 'Sure.' if you ask him a question.

    He answers 'Whoa, chill out!' if you yell at him.

    He says 'Fine. Be that way!' if you address him without actually saying
    anything.

    He answers 'Whatever.' to anything else.


Instructions:
    Run the test file, and fix each of the errors in turn. When you get the
    first test to pass, go to the first pending or skipped test, and make
    that pass as well. When all of the tests are passing, feel free to
    submit.

    Remember that passing code is just the first step. The goal is to work
    towards a solution that is as readable and expressive as you can make
    it.

    Please make your solution as general as possible. Good code doesn't just
    pass the test suite, it works with any input that fits the
    specification.
'''
__author__ = "Shimon Bollinger <shimon.bollinger@gmail.com>"
__date__ = "Fri Nov  7 22:47:38 2014"

__version__ = "1"
__credits__ = """Inspired by the 'Deaf Grandma' exercise in Chris Pine's
Learn to Program tutorial. (http://pine.fm/LearnToProgram/?Chapter=06)"""


def hey(what = ''):
    "Returns Bob's response to whatever you ask"

    what = what.strip()

    if what == '':
        return "Fine. Be that way!"
    elif what.isupper(): # Shouting is ALL CAPS with at least one alphabetic
        return "Whoa, chill out!"
    elif what[-1] == '?':
        return "Sure."
    else:
        return "Whatever."

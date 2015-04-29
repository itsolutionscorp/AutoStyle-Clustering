#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""Exercism exercise #1: bob

Provides the code necessary to pass the tests provided with the first
Exercism.io exercise.

Written using Python 3.4
"""


def hey(what_bob_heard):
    """Based on what Bob hears provide the appropriate response.

    Args:
        what_bob_heard: A string containing what Bob heard.

    Returns:
        A string containing Bob's response.

    Raises:
        N/A
    """
    if what_bob_heard.strip() == '':
        # Bob didn't hear anything.
        return 'Fine. Be that way!'
    elif what_bob_heard.isupper():
        # Bob was yelled at.
        return 'Woah, chill out!'
    elif what_bob_heard.endswith('?'):
        # Bob was asked a question but wasn't yelled at.
        return 'Sure.'
    else:
        return 'Whatever.'

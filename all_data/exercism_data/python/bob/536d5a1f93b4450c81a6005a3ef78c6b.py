#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""Exercism exercise #1: bob

Provides the code necessary to pass the tests provided with the first
Exercism.io exercise.

Written using Python 3.4
"""


def _is_yelling(what_bob_heard):
    return what_bob_heard.isupper()


def _is_a_question(what_bob_heard):
    return what_bob_heard.endswith('?')


def _is_silence(what_bob_heard):
    return what_bob_heard.strip() == ''


def hey(what_bob_heard):
    """Based on what Bob hears provide the appropriate response.

    Args:
        what_bob_heard: A string containing what Bob heard.

    Returns:
        A string containing Bob's response.

    Raises:
        N/A
    """
    if _is_silence(what_bob_heard):
        return 'Fine. Be that way!'
    elif _is_yelling(what_bob_heard):
        return 'Woah, chill out!'
    elif _is_a_question(what_bob_heard):
        return 'Sure.'
    else:
        return 'Whatever.'

#!/usr/bin/env python
# -*- coding: utf-8 -*-

from __future__ import unicode_literals
import unittest


def hey(value):
    """ This method handles what we're saying to Bob.
        """
    # Clean up value.
    value = value.rstrip()
    length = len(value)

    if value == '':
        return 'Fine. Be that way!'

    # If we're yelling, as in: If all the letters are capital AND
    # there are letters in the string (we test that with the second
    # clause of the if statement -- if there are only non-alpha chars
    # that value.lower() != value clause will fail.
    if value.upper() == value and value.lower() != value:
        return 'Whoa, chill out!'

    # If there's a question mark on the last character of value, 
    # it's a question.
    if value[length - 1] == '?':
        return 'Sure.'

    return 'Whatever.'

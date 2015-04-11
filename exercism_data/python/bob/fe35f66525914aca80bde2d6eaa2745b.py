# -*- coding: utf-8 -*-

from __future__ import unicode_literals
from string import whitespace


def hey(s):
    def is_silent(s):
        """ Silence s an empty or space/whitespce filled string. """
        return True if not s or s.replace(' ', '') in whitespace else False

    def is_question(s):
        """ A question always ends with a '?'. """
        return True if s[-1] == '?' else False

    def is_shout(s):
        """
        A shout is always in ALL CAPS.
        '!' at the end is not shouting.

        """
        r = False
        for char in s:
            # Simultaneous validation of lower and upper cases.
            # A single lower case letter makes it a non shout.
            # If no letters (punctuation or numbers only), is not a shout.
            if char.islower():
                r = False
                break
            elif not r and char.isupper():
                r = True
        return r

    if is_silent(s):
        r = 'Fine. Be that way!'
    elif is_shout(s):
        r = 'Whoa, chill out!'
    elif is_question(s):
        r = 'Sure.'
    else:
        r = 'Whatever.'

    return r

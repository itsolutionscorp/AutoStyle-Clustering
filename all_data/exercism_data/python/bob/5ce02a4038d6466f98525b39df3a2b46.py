#!/usr/bin/env python
# -*- coding: utf-8 -*-

from __future__ import unicode_literals     # This should help with Python 3 compatibility
import re

reply = ["Sure.", "Whoa, chill out!", "Fine. Be that way!", "Whatever."]

# [^A-Z ] = Not a capital Letter
# .{0,} = Repeat preceding expression 0 or more times (This checks almost all characters)
# \?& = The last character is a Question mark
question = re.compile(r'[^A-Z ].{0,}\?$')
# [A-Z,] = Checks that that first character is from A to Z
# [A-Z0-9 .,!?ÜÄ]+$ = Checks that the 2nd character on is within the set
# ^[A-Z0-9 .,!?%^*@#$()ÜÄ]+ = Checks that all characters until the last are within the set
# !$ = Checks that the last character is an explimation mark
yelling = re.compile(r'^[A-Z][A-Z0-9 .,!?ÜÄ]+$|^[A-Z0-9 .,!?%^*@#$()ÜÄ]+!$')
# ^$ = Null string will be valid with this expression.
# ^[ .!?\t\n\r\f]+$ = Checks that all characters are within the set.
addressing = re.compile(r'^$|^[ .!?\t\n\r\f]+$')

def hey(what):
    # Strips the whitespace.
    what = what.strip()

    if question.search(what):
        return reply[0]
    elif yelling.search(what):
        return reply[1]
    elif addressing.search(what):
        return reply[2]
    else:
        return reply[3]

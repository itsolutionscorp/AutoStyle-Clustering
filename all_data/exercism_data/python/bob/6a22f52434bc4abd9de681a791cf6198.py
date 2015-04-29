#!/usr/bin/env python
# -*- coding: utf-8 -*-


def hey(string):
    # These tests make the unit tests pass, but the unit tests do not
    # match the README
    if string.isspace() or not string:
        return u"Fine. Be that way!"
    elif string.isupper():
        return u"Whoa, chill out!"
    elif string[-1] == '?':
        return u"Sure."
        
    return u"Whatever."

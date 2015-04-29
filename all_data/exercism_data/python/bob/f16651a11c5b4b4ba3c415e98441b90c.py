# -*- coding: utf-8 -*-
from __future__ import unicode_literals


def hey(s):
    if s.isupper():
        return 'Whoa, chill out!'
    elif s == '' or s.isspace():
        return 'Fine. Be that way!'
    elif s[-1] == '?' :
        return 'Sure.'
    else:
        return "Whatever."

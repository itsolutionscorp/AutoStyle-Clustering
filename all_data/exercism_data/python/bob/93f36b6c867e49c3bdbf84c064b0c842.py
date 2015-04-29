# -*- coding: utf-8 -*-
from __future__ import unicode_literals


def hey(s):
    if s.isupper():
        return 'Whoa, chill out!'
    elif s != '' and s[-1] == '?' :
        return 'Sure.'
    elif s == '' or s.isspace():
        return 'Fine. Be that way!'
    else:
        return "Whatever."

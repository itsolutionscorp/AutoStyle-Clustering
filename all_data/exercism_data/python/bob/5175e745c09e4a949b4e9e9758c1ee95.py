#!/usr/bin/env python

import re

def hey(s):
    if not s or re.match(r'^\s*$',s):
        return u'Fine. Be that way!'
    if s.isupper():
        return u'Whoa, chill out!'
    if re.match(r'.*\?$',s):
        return u'Sure.'
    return u'Whatever.'

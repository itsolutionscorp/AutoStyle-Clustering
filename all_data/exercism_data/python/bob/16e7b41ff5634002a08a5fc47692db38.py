#!/usr/bin/env python

import re

def hey(s):
    if s.isupper():
        return u'Whoa, chill out!'
    if re.match(r'.*\?$',s):
        return u'Sure.'
    if re.match(r'^\s*$',s):
        return u'Fine. Be that way!'
    return u'Whatever.'

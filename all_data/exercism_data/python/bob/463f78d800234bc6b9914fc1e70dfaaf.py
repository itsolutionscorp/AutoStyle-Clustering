#!/usr/bin/env python

import re
import unicodedata

def strip_accents(s):
    #shamelessy borrowed from
    #http://stackoverflow.com/questions/517923/what-is-the-best-way-to-remove-accents-in-a-python-unicode-string
    return ''.join(c for c in unicodedata.normalize('NFD', s)
                  if unicodedata.category(c) != 'Mn')

def hey(said):
    said = strip_accents(said)
    if re.match(r'^[A-Z]+$',re.sub(r'[^a-zA-Z]','',said)):
        return u'Whoa, chill out!'
    if re.match(r'.*\?$',said):
        return u'Sure.'
    if re.match(r'^\s*$',said):
        return u'Fine. Be that way!'
    return u'Whatever.'

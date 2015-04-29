# -*- coding: utf-8 -*-

from __future__ import unicode_literals
import string

def has_letters(msg):
    # checks msg to see if standard alphabet or umlauted vowels appear.
    # returns set of letters that appear which can be used as truth value.
    ltrs = string.letters + 'ÄËÏÖÜäëïöü'
    return(set(msg).intersection(set(ltrs)))

def hey(msg):
    msg = msg.strip()
    if not msg:
        return('Fine. Be that way!')
    elif msg[:].upper() == msg and has_letters(msg):
        return('Whoa, chill out!')
    elif msg[-1] == '?':
        return('Sure.')
    else:
        return('Whatever.')

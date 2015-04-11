# -*- coding: utf-8 -*-
from __future__ import unicode_literals
import string
#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if is_nonsense(what):
        return 'Fine. Be that way!'
    elif is_yell(what):
        return 'Whoa, chill out!'
    elif is_question(what):
        return 'Sure.'
    else:
        return 'Whatever.'

def is_question(what):
    return what.strip().endswith('?')

def is_yell(what):
    return what.isupper()

def is_nonsense(what):
    return len(what.strip()) == 0

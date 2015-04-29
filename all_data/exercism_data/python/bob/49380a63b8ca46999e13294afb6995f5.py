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
    is_number = [c in (list(string.digits) + list(string.whitespace) + list(string.punctuation)) for c in what]
    if all(is_number):
        return False
    lowercase = list(string.ascii_lowercase) + ['ü', 'ö', 'ä']
    is_uppercase = [not (c in lowercase) for c in what]
    return all(is_uppercase)

def is_nonsense(what):
    return len(what.strip()) == 0

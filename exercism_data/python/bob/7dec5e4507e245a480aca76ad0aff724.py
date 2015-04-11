# -*- coding: utf-8 -*-
#
# Skeleton file for the Python "Bob" exercise.
#

import string
import unicodedata

def _remove_accents(what):
    nkfd_form = unicodedata.normalize('NFKD', what)
    return u"".join([c for c in nkfd_form if not unicodedata.combining(c)])

def is_question(what):
    return what != '' and what[-1] == '?'

def is_yelling(what):
    letters = [c for c in _remove_accents(what) if c in string.ascii_letters]
    if not letters:
        return False
    lowercase = [c for c in letters if c in string.ascii_lowercase]
    if not lowercase: 
        return True
    return False

def is_empty(what):
    return not what

def hey(what):
    what = ''.join([c for c in what if c not in string.whitespace])
    if is_empty(what):
        return 'Fine. Be that way!'
    elif is_yelling(what):
        return 'Whoa, chill out!'
    elif is_question(what):
        return 'Sure.'
    return 'Whatever.'

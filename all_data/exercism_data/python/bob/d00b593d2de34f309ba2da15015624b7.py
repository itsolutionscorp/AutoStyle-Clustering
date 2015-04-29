#!/usr/bin/env python3

def hey(what=''):
    """hey Bob"""
    what = what.strip().replace(',', '')
    # input is empty
    if what == '': return 'Fine. Be that way!'

    # only caps
    if what.isupper(): return 'Whoa, chill out!'

    # question
    if what.endswith('?'): return 'Sure.'

    return 'Whatever.'

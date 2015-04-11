# -*- coding: utf-8 -*-
#
# exercism.com: python/bob
# Simple Turing test bot

import re

def hey(what):
    what_cleaned = what.strip()

    # Was anything said?
    if not what_cleaned:
        return 'Fine. Be that way!'

    # All caps and contains at least one letter
    if what_cleaned.upper() == what_cleaned and re.match('.*[a-zA-Z]+.*', what_cleaned):
        return 'Whoa, chill out!'

    punctuation = what_cleaned[-1]
    if punctuation == '?':
        return 'Sure.'

    # Default response
    return 'Whatever.'

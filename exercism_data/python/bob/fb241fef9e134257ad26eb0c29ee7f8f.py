#!/usr/bin/python

import re

QUESTION = re.compile(r".+\?$")
QUESTION_RESPONSE = 'Sure.'
YELL = re.compile(r"(?:[A-Z]+[\s])[!]?$")
YELL_RESPONSE = 'Whoa, chill out!'
EMPTY_RESPONSE = 'Fine. Be that way!'
DEFAULT_RESPONSE = 'Whatever.'


def hey(saying):
    if QUESTION.search(saying) and not saying.isupper():
        return QUESTION_RESPONSE
    elif YELL.search(saying) or saying.isupper():
        return YELL_RESPONSE
    elif saying.isspace() or not saying:
        return EMPTY_RESPONSE
    else:
        return DEFAULT_RESPONSE

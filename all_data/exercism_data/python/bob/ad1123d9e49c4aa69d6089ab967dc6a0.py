#!/usr/bin/env python
from collections import Counter
import string

QUESTION_RESPONSE = 'Sure.'
YELL_RESPONSE = 'Whoa, chill out!'
EMPTY_RESPONSE = 'Fine. Be that way!'
OTHER_RESPONSE = 'Whatever.'

def hey(input):
    if not input.strip():
        return EMPTY_RESPONSE
    elif sum(Counter(input)[letter] for letter in string.ascii_letters) > 0 \
            and input == input.upper():
        return YELL_RESPONSE
    elif input[-1] == '?':
        return QUESTION_RESPONSE
    else:
        return OTHER_RESPONSE

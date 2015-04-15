# -*- coding: utf-8 -*-
from __future__ import unicode_literals


def hey(sayed):
    if not sayed.strip():  # strip spaces, tabs and newlines
        return 'Fine. Be that way!'
    elif sayed.isupper():  # all uppercase means shouting
        return 'Whoa, chill out!'
    elif sayed.endswith('?'):  # ending with ? means a question
        return 'Sure.'
    else:
        return 'Whatever.'

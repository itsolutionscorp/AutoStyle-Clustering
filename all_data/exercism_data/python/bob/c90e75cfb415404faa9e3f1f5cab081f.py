# -*- coding: utf-8 -*-
import string

def hey(dialog):
    if dialog.strip() == "":
        return 'Fine. Be that way!'
    elif dialog.isupper():
        return 'Whoa, chill out!'
    elif dialog[-1:] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'

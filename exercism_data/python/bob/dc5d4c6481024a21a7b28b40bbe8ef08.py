# -*- coding: utf-8 -*-
import string

def hey(dialog):
    if dialog.strip() == "":
        return 'Fine. Be that way!'
    elif any(i.isalpha() for i in dialog) and dialog == dialog.upper():
        return 'Whoa, chill out!'
    elif dialog[-1:] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'

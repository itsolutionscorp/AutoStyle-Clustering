# -*- coding: utf-8 -*-

def hey(says):
    trimmed = says.strip()
    if trimmed == '':
        return 'Fine. Be that way!'
    elif trimmed.isupper():
        return 'Whoa, chill out!'
    elif trimmed.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'

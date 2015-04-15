# -*- coding: utf-8 -*-

def hey(says):
    if says.strip() == '':
        return 'Fine. Be that way!'
    elif says.isupper():
        return 'Whoa, chill out!'
    elif says.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'

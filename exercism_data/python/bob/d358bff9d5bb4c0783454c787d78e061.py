# -*- coding: utf-8 -*-


def hey(string):
    '''
    returns 'Sure.' when asked a question?
    returns 'Whoa, chill out!' when told in CAPPS
    returns 'Fine. Be that way!' when addressed with nothing
    returns 'Whatever.' for anything else
    '''
    if not string or string.isspace():
        return 'Fine. Be that way!'
    elif string.isupper():
        return 'Whoa, chill out!'
    elif string.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'

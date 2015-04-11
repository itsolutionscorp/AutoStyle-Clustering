# -*- coding: utf-8 -*-

import string

def hey(what):

    try:
        what.encode('ascii')
    except UnicodeEncodeError:
        # catching strings with unicode characters
        if what.isupper():
            return 'Whoa, chill out!'
        else:
            return 'Whatever.'

    if what.endswith(('?', ' ')) and not what.isupper():
        return 'Sure.'
    elif what.endswith(('.', '!')):
        if what.isupper():
            return 'Whoa, chill out!'
        else:
            return 'Whatever.'
    elif what.endswith(tuple(string.digits)):
            return 'Whatever.'
    elif what.endswith(tuple(string.whitespace)) or what == '':
        return 'Fine. Be that way!'
    elif what.isupper():
        return 'Whoa, chill out!'

    return

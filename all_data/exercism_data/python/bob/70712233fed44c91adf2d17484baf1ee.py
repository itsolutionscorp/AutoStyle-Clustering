# -*- coding: utf-8 -*-

def hey(what):

    if what.strip() == '':
        return u'Fine. Be that way!'
    elif what.strip().isupper():
        return u'Whoa, chill out!'
    elif what.strip()[-1:] == '?':
        return u'Sure.'
    return u'Whatever.'

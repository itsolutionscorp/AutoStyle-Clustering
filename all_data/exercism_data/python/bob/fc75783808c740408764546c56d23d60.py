# -*- coding: utf-8 -*-
#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if what.find('?', len(what)-1) != -1 and what.isupper() != True:
        return u'Sure.'
    elif what.isupper():
        return u'Whoa, chill out!'
    elif len(what) == 0 or what.isspace():
        return u'Fine. Be that way!'
    else:
        return u'Whatever.'

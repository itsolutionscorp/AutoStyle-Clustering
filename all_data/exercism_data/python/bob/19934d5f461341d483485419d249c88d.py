# -*- coding: utf-8 -*-
# Skeleton file for the Python "Bob" exercise.
#

import string


def hey(what):
    response, yelling = False, False
    for s in what:
        if s not in string.whitespace:
            response = True
            if s in string.ascii_letters or s in u'äëïöü':
                yelling = True
                if s in string.ascii_lowercase or s in u'äëïöü':
                    yelling = False
                    break
    if not response:
        return 'Fine. Be that way!'
    elif yelling:
        return 'Whoa, chill out!'
    elif what.rstrip()[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'

#
# Skeleton file for the Python "Bob" exercise.
#

import re

def hey(what):
    what = sanitize(what)

    if is_silent(what):
        return "Fine. Be that way!"
    elif is_yelling(what):
        return "Whoa, chill out!"
    elif is_question(what):
        return "Sure."
    else:
        return "Whatever."

def sanitize(what):
    return what.strip()

def is_yelling(what):
    return what.isupper()

def is_silent(what):
    return what == ''

def is_question(what):
    return what.endswith('?')

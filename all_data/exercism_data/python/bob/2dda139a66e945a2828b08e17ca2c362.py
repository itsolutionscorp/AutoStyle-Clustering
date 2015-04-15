# -*- coding: utf-8 -*-

#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    if what == None or what.strip() == "":
        return 'Fine. Be that way!'
    elif what.isupper():
        return 'Whoa, chill out!'
    elif what[-1] == ".":
        return "Whatever."
    elif what.strip()[-1] == "?":
        return 'Sure.'
    else:
        return "Whatever."

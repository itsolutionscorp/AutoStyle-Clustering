# -*- coding: utf-8 -*-
# Skeleton file for the Python 'Bob' exercise.
#


def hey(what):
    if is_silence(what):
        return 'Fine. Be that way!'
    elif is_shouting(what):
        return 'Whoa, chill out!'
    elif is_question(what):
        return 'Sure.'
    else:
        return 'Whatever.'


def is_silence(what):
    return len(what.strip()) == 0


def is_shouting(what):
    return what.isupper()


def is_question(what):
    return what.endswith('?') and not what.isupper()

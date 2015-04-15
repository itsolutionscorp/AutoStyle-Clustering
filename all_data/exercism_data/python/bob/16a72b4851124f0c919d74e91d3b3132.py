#
# Skeleton file for the Python "Bob" exercise.
#
import re

QUESTIONED = 'Sure.'

YELLED = 'Whoa, chill out!'

SAID_NOTHING = 'Fine. Be that way!'

DEFAULT = 'Whatever.'

def clean(what):
    if what is None:
        return ""
    return what.strip()

def is_yell(what):
    letters = []
    for char in what:
        if char.isalpha():
            letters.append(char)
    what = ''.join(letters)
    return what.isupper()

def hey(what):
    what = clean(what)
    if len(what) == 0:
        return SAID_NOTHING
    if is_yell(what):
        return YELLED
    if what[-1] == '?':
        return QUESTIONED

    return DEFAULT

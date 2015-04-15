#
# Skeleton file for the Python "Bob" exercise.
#
import os

def hey(what):
    what_raw=what.strip()
    if what_raw=='':
        return 'Fine. Be that way!'
    if what_raw.endswith('?'):
        return 'Sure.'
    if what.isupper():
        return 'Whoa, chill out!'
    return 'Whatever.'

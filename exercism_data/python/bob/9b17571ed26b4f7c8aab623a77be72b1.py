#
# Skeleton file for the Python "Bob" exercise.
#
import re


def hey(what):
    what = (what or '').strip()

    if not what:
        return 'Fine. Be that way!'

    if what.lower() != what.upper() and what == what.upper():
        return 'Whoa, chill out!'

    if what[len(what) - 1] == '?':
        return 'Sure.'

    return 'Whatever.'

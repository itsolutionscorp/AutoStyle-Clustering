#
# Skeleton file for the Python "Bob" exercise.
#
import re


def hey(what):
    what_without_spaces = re.sub('\s', '', what)

    if what == '' or what.isspace():
        return u'Fine. Be that way!'
    elif what_without_spaces.isupper():
        return u'Whoa, chill out!'
    elif what_without_spaces[-1] == '?':
        return u'Sure.'
    else:
        return u'Whatever.'

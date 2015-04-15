#
# Skeleton file for the Python "Bob" exercise.
#

import string


def hey(what):

    # no input
    if len(what.strip()) < 1:
        return 'Fine. Be that way!'

    # yelled input (CAPS)
    elif is_allcaps(what):
        return 'Whoa, chill out!'

    # ending question mark
    elif what[-1] == '?':
        return 'Sure.'

    # catchall
    else:
        return 'Whatever.'


def is_allcaps(s):
    # count lowercase and uppercase chars
    lowers, uppers = 0,0
    for l in s:
        if l.islower():
            lowers += 1
        elif l.isupper():
            uppers += 1

    if lowers == 0 and uppers > 0:
        return True # yelling
    else:
        return False # not yelling

#
# Skeleton file for the Python "Bob" exercise.
#

from string import digits, punctuation, whitespace

NON_ALPHA = set(digits + punctuation + whitespace)


def hey(what):

    alpha = filter(lambda x: x not in NON_ALPHA, what)

    if not what.strip():
        return 'Fine. Be that way!'
    elif alpha and alpha.upper() == alpha:
        return 'Whoa, chill out!'
    elif what.strip().endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'

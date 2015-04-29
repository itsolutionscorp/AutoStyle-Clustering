#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    what = what.strip() if what else ''
    if not what:
        return u'Fine. Be that way!'
    elif what.isupper():
        return u'Whoa, chill out!'
    elif what[-1] == '?':
        return u'Sure.'
    else:
        return u'Whatever.'

#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    if what.isspace() or not what:
        return 'Fine. Be that way!'
    elif what.isupper():
        return 'Whoa, chill out!'
    elif what[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'

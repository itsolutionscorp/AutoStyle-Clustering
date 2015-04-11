#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    if what.isupper():
        return 'Whoa, chill out!'
    elif what.strip().endswith('?'):
        return 'Sure.'
    elif not what or what.isspace():
        return 'Fine. Be that way!'
    return 'Whatever.'

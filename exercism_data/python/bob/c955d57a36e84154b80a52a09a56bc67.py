#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):

    if not what or what[-1] == '\t':
        return 'Fine. Be that way!'
    elif what.isupper():
        return 'Whoa, chill out!'
    elif what[-1] in ['?', ' ']:
        return 'Sure.'
    else:
        return 'Whatever.'

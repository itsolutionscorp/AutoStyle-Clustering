#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    reply = 'Fine. Be that way!'

    if len(what):
        if what.isspace():
            pass
        elif what.isupper():
            reply = 'Whoa, chill out!'
        elif what.endswith('?') or what.endswith(' '):
            reply = "Sure."
        else:
            reply = 'Whatever.'

    return reply

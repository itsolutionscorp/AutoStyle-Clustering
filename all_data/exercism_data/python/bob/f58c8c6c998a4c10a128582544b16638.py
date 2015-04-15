#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    what = what.strip()
    if not what:
        response = 'Fine. Be that way!'
    elif what.isupper():
        response = 'Whoa, chill out!'
    elif what[-1] == '?':
        response = 'Sure.'
    else:
        response = 'Whatever.'

    return response

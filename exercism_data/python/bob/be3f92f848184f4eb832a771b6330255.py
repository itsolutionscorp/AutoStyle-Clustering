#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):

    what = what.strip()
    if 0 == len(what):
        return 'Fine. Be that way!'
    elif what.isupper():
        return 'Whoa, chill out!'
    elif '?' == what[-1]:
        return 'Sure.'

    return "Whatever."

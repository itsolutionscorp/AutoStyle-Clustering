#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):

    # Check puntctuation and string length to determine intonation

    what = what.strip()

    if what.isupper():
        return 'Whoa, chill out!'
    elif len(what) == 0:
        return 'Fine. Be that way!'
    elif what[-1] == "?":
        return 'Sure.'
    else:
        return 'Whatever.'

    return
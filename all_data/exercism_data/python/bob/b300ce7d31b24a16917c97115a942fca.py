#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    print what.upper
    hasalpha = lambda what: any(w for w in what if w.isalpha())

    if len(what.strip()) == 0:
        return 'Fine. Be that way!'
    elif what.isupper():
        return 'Whoa, chill out!'
    elif '?' == what.strip()[-1]:
        return 'Sure.'
    else:
        return 'Whatever.'

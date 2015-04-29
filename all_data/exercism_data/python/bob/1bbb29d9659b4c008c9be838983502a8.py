#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    print what.upper
    hasalpha = lambda what: any(w for w in what if w.isalpha())

    if len(what.strip()) == 0:
        return 'Fine. Be that way!'
    elif hasalpha(what) and what.upper() == what:
        return 'Whoa, chill out!'
    elif '?' == what.strip()[-1]:
        return 'Sure.'
    else:
        return 'Whatever.'

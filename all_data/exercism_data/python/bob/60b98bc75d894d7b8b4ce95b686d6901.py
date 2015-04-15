#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    sure = "Sure."
    whoa = "Whoa, chill out!"
    fine = "Fine. Be that way!"
    whatever = "Whatever."

    if what.isupper():
        return whoa
    elif what.endswith('?') or what.endswith(' '):
        return sure
    elif what.isspace() or not what:
        return fine
    else:
        return whatever

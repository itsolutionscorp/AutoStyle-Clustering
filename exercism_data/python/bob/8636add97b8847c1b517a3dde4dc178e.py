#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    what = what.strip()
    if what:
        if (what == what.upper()) and any([c.isalpha() for c in what]):
            return "Whoa, chill out!"
        elif what[-1] == "?":
            return "Sure."
        else:
            return "Whatever."
    else:
        return "Fine. Be that way!"
    return

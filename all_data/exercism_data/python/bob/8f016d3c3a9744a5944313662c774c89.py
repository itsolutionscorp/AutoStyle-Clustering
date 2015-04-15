#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    what_strip = " ".join(what.split())
    if what_strip:
        if what_strip.isupper():
            return "Whoa, chill out!"
        elif what_strip[-1] == '?':
            return "Sure."
        else:
            return "Whatever."
    return "Fine. Be that way!"

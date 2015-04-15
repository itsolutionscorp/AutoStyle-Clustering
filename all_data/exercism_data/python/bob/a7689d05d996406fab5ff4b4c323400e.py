#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.rstrip()
    yelling = what.isupper()
    if what:
        question = (what[-1] == '?')
        blank = False
    else:
        question = False
        blank = True

    if yelling:
        return "Whoa, chill out!"
    elif question:
        return "Sure."
    elif blank:
        return "Fine. Be that way!"
    else:
        return "Whatever."

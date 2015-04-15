#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):

    if is_shouting(what):
        return "Whoa, chill out!"
    elif is_questionning(what):
        return "Sure."
    elif is_empty(what):
        return "Fine. Be that way!"
    else:
        return "Whatever."


def is_shouting(what):
    return what.isupper()


def is_questionning(what):
    return what.strip().endswith("?")


def is_empty(what):
    return len(what.strip()) == 0

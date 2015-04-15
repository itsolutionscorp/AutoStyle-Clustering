#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    trimmed = what.rstrip()
    if len(trimmed) == 0:
        return "Fine. Be that way!"
    if trimmed.isupper():
        return "Whoa, chill out!"
    if trimmed[-1] == "?":
        return "Sure."

    return "Whatever."

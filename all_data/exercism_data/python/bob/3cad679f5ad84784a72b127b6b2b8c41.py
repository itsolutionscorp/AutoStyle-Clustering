#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if what.rstrip().endswith("?") and not what.isupper():
        return "Sure."
    elif what.isupper():
        return "Whoa, chill out!"
    elif not what.strip():
        return "Fine. Be that way!"
    else:
        return "Whatever."

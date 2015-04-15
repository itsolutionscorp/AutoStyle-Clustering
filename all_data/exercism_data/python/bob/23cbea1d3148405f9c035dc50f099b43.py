#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if not what:
        ret = "Fine. Be that way!"
    elif what.endswith("?"):
        ret = "Sure."
    elif what.endswith("!"):
        ret = "Whoa, chill out!"
    else:
        ret = "Whatever."
    return ret

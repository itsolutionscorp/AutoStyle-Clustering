#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
    """ talk to bob the babbler"""
    if not what.strip():
        return "Fine. Be that way!"
    elif what.isupper():
        return "Whoa, chill out!"
    elif what.strip()[-1] == "?":
        return "Sure."
    else:
        return "Whatever."
    return

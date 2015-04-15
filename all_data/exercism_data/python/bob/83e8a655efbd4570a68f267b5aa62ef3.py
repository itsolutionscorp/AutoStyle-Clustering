#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
    """ talk to bob the babbler"""
    if not what or what.strip() == "":
        return "Fine. Be that way!"
    elif what.isupper():
        return "Whoa, chill out!"
    elif what[-1:] == "?":
        return "Sure."
    else:
        return "Whatever."
    return

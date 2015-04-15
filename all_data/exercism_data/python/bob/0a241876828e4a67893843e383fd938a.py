#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if (not what) or (what.isspace()):
        return "Fine. Be that way!"
    elif (what.endswith('!') and what.isupper()) or (what.isupper()):
        return "Whoa, chill out!"
    elif what.strip().endswith('?'):
        return "Sure."
    else:
        return "Whatever."

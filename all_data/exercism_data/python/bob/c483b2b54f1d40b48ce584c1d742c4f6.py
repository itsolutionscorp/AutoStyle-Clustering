#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if len(what.strip()) == 0:
        return "Fine. Be that way!"
    elif what.strip()[-1] == "?" and not what.isupper():
        return "Sure."
    elif what.isupper():
        return "Whoa, chill out!"
    else:
        return "Whatever."

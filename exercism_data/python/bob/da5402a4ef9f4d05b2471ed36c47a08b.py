#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if what.isupper():
        return "Whoa, chill out!"
    elif what.rstrip().endswith("?"):
        return "Sure."
    elif not what.strip():
        return "Fine. Be that way!"
    else:
        return "Whatever."

#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()
    if what:
        if what.isupper():
            return "Whoa, chill out!"
        if what[-1] == "?":
            return "Sure."
        return "Whatever."
    return "Fine. Be that way!"

#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.lstrip().rstrip()
    if len(what) == 0:
        return "Fine. Be that way!"
    if what.isupper():
        return "Whoa, chill out!"
    if what.endswith("?"):
        return "Sure."
    return "Whatever."

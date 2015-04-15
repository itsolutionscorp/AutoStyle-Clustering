#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()
    if what.isupper():
        return "Whoa, chill out!"
    if len(what) > 0 and what[-1] == '?':
        return "Sure."
    if what == "":
        return "Fine. Be that way!"
    return "Whatever."

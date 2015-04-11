#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if not (isinstance(what, unicode) & isinstance(what, str)):
        what = str(what);
    what = what.strip();
    if len(what) == 0:
        return "Fine. Be that way!"
    if what.isupper():
        return "Whoa, chill out!"
    if what.endswith("?"):
        return "Sure."
    return "Whatever."

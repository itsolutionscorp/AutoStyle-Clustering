#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    test = unicode(what.encode("utf-8"), "utf-8").strip()
    if len(test) == 0:
        return "Fine. Be that way!"
    if test.isupper():
        return "Whoa, chill out!"
    if test.endswith("?"):
        return "Sure."
    return "Whatever."

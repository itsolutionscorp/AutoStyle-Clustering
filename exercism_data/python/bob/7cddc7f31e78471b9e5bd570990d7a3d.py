#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()
    if len(what) is 0:
        return "Fine. Be that way!"
    elif what.isupper():
        return "Whoa, chill out!"
    elif what.endswith("?"):
        return 'Sure.'
    return "Whatever."
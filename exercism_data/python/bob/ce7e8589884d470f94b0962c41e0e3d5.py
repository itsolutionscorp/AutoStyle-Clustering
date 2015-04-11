#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()
    if len(what) == 0:
        return "Fine. Be that way!"
    elif _upper_count(what) and _upper_count(what.upper()) == _upper_count(what):
        return "Whoa, chill out!"
    elif what.endswith("?"):
        return "Sure."
    else:
        return "Whatever."


def _upper_count(what):
    count = 0
    for l in what:
        if l.istitle():
            count += 1
    return count

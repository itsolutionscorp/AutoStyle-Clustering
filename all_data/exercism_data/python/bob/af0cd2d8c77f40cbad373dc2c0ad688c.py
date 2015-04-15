#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()

    if what == '':
        return "Fine. Be that way!"

    elif not any(c.islower() for c in what) and any(c.isupper() for c in what):
        return "Whoa, chill out!"

    elif what.endswith("?"):
        return "Sure."

    elif all(not c.isalpha() for c in what):
        return "Whatever."

    return "Whatever."

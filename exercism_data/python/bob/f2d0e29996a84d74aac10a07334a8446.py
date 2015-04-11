#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()
    if what.upper() == what and any(c.isalpha() for c in what):
        return "Whoa, chill out!"
    elif what.endswith("?"):
        return "Sure."
    elif what == "" or what is None:
        return "Fine. Be that way!"
    else:
        return "Whatever."


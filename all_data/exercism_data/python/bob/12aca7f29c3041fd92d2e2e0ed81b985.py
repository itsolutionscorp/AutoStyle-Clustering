#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    """
    :type what: str
    """

    if what.isspace() or len(what) == 0:
        return "Fine. Be that way!"

    what = what.strip()

    if what.isupper():
        return "Whoa, chill out!"

    elif what[len(what)-1] == "?":
        return "Sure."

    else:
        return "Whatever."

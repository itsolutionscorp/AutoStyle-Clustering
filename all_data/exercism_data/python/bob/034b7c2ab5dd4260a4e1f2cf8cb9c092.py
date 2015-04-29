#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    # does it not have at least one alphanumeric?
    if not any([char.isalnum() for char in what]):
        return "Fine. Be that way!"

    # is it all caps?
    elif what.isupper():
        return "Whoa, chill out!"

    # does it end with a '?'
    elif what[-1] == "?":
        return "Sure."

    else:
        return "Whatever."

    return

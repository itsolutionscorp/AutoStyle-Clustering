#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if any(c.isalnum() for c in what) is False:  #Check to see if any characters are alphanumeric
        return "Fine. Be that way!"
    elif what[:-1].isupper():
        return "Whoa, chill out!"
    elif what[-1] == "?":
        return "Sure."
    else:
        return "Whatever."

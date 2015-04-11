#
# Submission file for the Python "Bob" exercise.
#
def hey(what):
    
    if isinstance(what, basestring) is False:
        return "Whatever."

    if what == "" or what.isspace() is True:
        return "Fine. Be that way!"
    elif what.isupper() is True:
        return "Whoa, chill out!"
    elif what[-1] == "?":
        return "Sure."
    else:
        return "Whatever."

#
# Submission file for the Python "Bob" exercise.
#
def hey(what):
    
    if isinstance(what, basestring) is False:
        return "Whatever."

    if what == "" or what.isspace():
        return "Fine. Be that way!"
    elif what.isupper():
        return "Whoa, chill out!"
    elif what.endswith('?'):
        return "Sure."
    else:
        return "Whatever."

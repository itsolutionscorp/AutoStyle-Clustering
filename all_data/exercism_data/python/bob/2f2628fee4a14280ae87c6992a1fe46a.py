#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if what == "" or what.isspace():
        return "Fine. Be that way!"
    elif what.isupper() == True:
        return "Whoa, chill out!"
    elif what.endswith('?'):
        return "Sure."
    else:
        return "Whatever."

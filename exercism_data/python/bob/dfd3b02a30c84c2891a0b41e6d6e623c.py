#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    #Finding if the last character is a question mark.
    if what.isupper():
        return "Whoa, chill out!"
    elif what.endswith('?'):
        return "Sure."
    elif what.isspace() or len(what) == 0:
        return "Fine. Be that way!"
    else:
        return "Whatever."

#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if what.isupper():
        return "Whoa, chill out!"
    elif what[-1:] == "?":
        return "Sure." 
    if what.isspace() or what == '':
        return "Fine. Be that way!"
    return "Whatever."

#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if len(what)==0:
        return "Fine. Be that way!"
    if what.isupper():
        return "Whoa, chill out!"
    elif what == "?" or what[len(what)-1] == "?":
        return "Sure."
    elif what.isspace():
        return "Fine. Be that way!"
    else:
        return "Whatever."

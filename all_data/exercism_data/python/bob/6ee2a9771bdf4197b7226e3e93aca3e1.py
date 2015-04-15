#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if what == "" or what.isspace() == True:
        return "Fine. Be that way!"
    elif what.isupper() == True:
        return "Whoa, chill out!"
    elif what[len(what) - 1] == "?":
        return "Sure."
    else:
        return "Whatever."

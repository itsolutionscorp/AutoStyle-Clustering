#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if what == "" or what.isspace() == True:
        return "Fine. Be that way!"
    if what[:-1].isupper():
        return "Whoa, chill out!"
    if what[-1] == "?":
        return "Sure."
    else:
        return "Whatever."

#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if what != "":
        if what[-1] == "?" and what.isupper():
            return "Whoa, chill out!"
        elif what[-1] == "?":
            return "Sure."
        elif what.isupper() and what[-1] != "!":
            return "Whoa, chill out!"
        elif what[-1] == "!" or (what[-1] == "!" and what.isupper()):
            return "Whoa, chill out!"
        elif what[-1] == "\t":
            return "Fine. Be that way!"
        elif what[-1] == "?" and what.isupper():
            return "Whoa, chill out!"
        elif what[-1] == " ":
            return "Sure."
        else:
            return "Whatever."
    else:
        return "Fine. Be that way!"

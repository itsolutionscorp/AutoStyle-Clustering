#Solution to Bob exercise
def hey(what):
    if what.isspace() or what == "":
        return "Fine. Be that way!"
    elif what.isupper():
        return "Whoa, chill out!"
    elif what.strip()[-1] == "?":
        return "Sure."
    else:
        return "Whatever."

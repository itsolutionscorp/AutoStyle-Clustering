def hey(what):
    if what.strip():
        if what.isupper():
            return "Whoa, chill out!"
        if what[-1] == "?":
            return "Sure."
        else:
            return "Whatever."
    else:
        return "Fine. Be that way!"

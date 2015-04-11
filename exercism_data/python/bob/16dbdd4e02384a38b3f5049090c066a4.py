def hey(what):
    what = what.strip()
    if what == "":
        return "Fine. Be that way!"
    elif what.isupper():
        return "Whoa, chill out!"
    elif what.endswith("?"):
        return "Sure."
    return "Whatever."

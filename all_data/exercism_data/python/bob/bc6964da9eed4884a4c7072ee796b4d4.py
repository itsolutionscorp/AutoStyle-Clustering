def hey(what):
    what = what.strip()

    # substring contain all the alphabetical characters
    alpha = "".join(c for c in what if c.isalpha())

    if not "".join(what.split()):
        return "Fine. Be that way!"
    elif alpha.isupper():
        return "Whoa, chill out!"
    elif what.endswith("?"):
        return "Sure."
    else:
        return "Whatever."

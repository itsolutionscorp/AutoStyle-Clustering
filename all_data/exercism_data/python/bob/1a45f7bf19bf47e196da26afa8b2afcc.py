def hey(what):
    if (len(what) == 0):
        return "Fine. Be that way!"

    if (what.upper() == what):
        return "Whoa, chill out!"

    if (what.endswith("?")):
        return "Sure."

    return "Whatever."

def hey(inp):
    if not inp.split():
        return "Fine. Be that way!"
    if inp.isupper():
        return "Whoa, chill out!"
    if inp[-1] == "?":
        return "Sure."
    return "Whatever."

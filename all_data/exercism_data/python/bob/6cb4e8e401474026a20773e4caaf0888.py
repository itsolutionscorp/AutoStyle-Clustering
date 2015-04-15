def hey(inp):
    if not inp.split():
        return "Fine. Be that way!"
    elif inp.isupper():
        return "Whoa, chill out!"
    elif inp[-1] == "?":
        return "Sure."
    else:
        return "Whatever."

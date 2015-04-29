def hey(t):
    if t.isspace() or t == "":
        return "Fine. Be that way!"
    elif t.isupper():
        return "Whoa, chill out!"
    elif t[-1] == "?":
        return "Sure."
    else:
        return "Whatever."

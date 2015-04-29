def hey(t):
    if t.isspace() or len(t) == 0:
        return "Fine. Be that way!"
    elif t.isupper():
        return "Whoa, chill out!"
    elif t.endswith("?"):
        return "Sure."
    else:
        return "Whatever."

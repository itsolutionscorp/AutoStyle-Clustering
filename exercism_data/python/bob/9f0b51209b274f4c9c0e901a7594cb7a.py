def hey(what):

    q = what.strip()

    if q.isupper():
        return "Whoa, chill out!"
    elif q.endswith("?"):
        return "Sure."
    elif len(q) == 0:
        return "Fine. Be that way!"

    return "Whatever."

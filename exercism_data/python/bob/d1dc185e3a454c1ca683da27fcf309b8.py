def hey(what):
    if what.isupper():
        return "Whoa, chill out!"
    elif not what.strip():
        return "Fine. Be that way!"
    elif what.endswith('?'):
        return "Sure."
    else:
        return "Whatever."
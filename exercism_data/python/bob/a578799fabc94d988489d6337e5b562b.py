def hey(txt):

    if txt.isupper():
        return "Whoa, chill out!"
    elif txt.endswith('?'):
        return "Sure."
    elif not txt.strip():
        return "Fine. Be that way!"
    else:
        return "Whatever."

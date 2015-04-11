def hey(str):
    if not str or str.isspace():
        return "Fine. Be that way!"
    elif str.isupper():
        return "Woah, chill out!"
    elif str.endswith('?'):
        return "Sure."
    else:
        return "Whatever."

def hey(words):
    if words.isupper():
        return "Whoa, chill out!"
    elif words.endswith('?'):
        return "Sure."
    elif not words.strip():
        return "Fine. Be that way!"
    else:
        return "Whatever."

def hey(text):
    if (text.isupper()):
        return "Whoa, chill out!"

    if (text.endswith('?')):
        return "Sure."

    if (text.isspace() or len(text) == 0):
        return "Fine. Be that way!"

    return "Whatever."

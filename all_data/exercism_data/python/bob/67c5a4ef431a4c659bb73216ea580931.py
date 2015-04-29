def hey(words):
    if words.isupper():
        return "Whoa, chill out!"
    elif words.endswith('?'):
        return "Sure."
    elif words.isspace() or words == "":
        return "Fine. Be that way!"
    else:
        return "Whatever."

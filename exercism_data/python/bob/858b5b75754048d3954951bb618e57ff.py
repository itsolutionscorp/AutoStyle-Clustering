def hey(inputString):
    inputString = inputString.strip()

    if inputString.isupper():
        return "Whoa, chill out!"

    if inputString.endswith('?'):
        return "Sure."

    if not inputString:
        return "Fine. Be that way!"

    return "Whatever."

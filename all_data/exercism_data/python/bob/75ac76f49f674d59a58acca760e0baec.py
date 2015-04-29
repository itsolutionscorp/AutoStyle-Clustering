def hey(inputString):
    if inputString.isupper():
        return "Whoa, chill out!"

    if inputString.isspace() or not inputString:
        return "Fine. Be that way!"

    if inputString[-1] == '?':
        return "Sure."

    return "Whatever."

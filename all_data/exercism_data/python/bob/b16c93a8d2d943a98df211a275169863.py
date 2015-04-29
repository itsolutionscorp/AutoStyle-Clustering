def hey(string):
    if not string or string.isspace():
        return "Fine. Be that way!"
    if string.isupper():
        return "Whoa, chill out!" 
    if string.endswith("?"):
        return "Sure."
    return "Whatever."

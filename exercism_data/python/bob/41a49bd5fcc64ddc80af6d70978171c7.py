def hey(str):
    if str.isupper():
        return "Whoa, chill out!"

    if not str.strip():
        return "Fine. Be that way!"

    if str[-1] == "?":
        return "Sure."

    return "Whatever."

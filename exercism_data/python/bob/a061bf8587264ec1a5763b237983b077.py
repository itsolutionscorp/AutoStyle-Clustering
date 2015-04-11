def hey(str):
    str = str.strip()

    if str.isupper():
        return "Whoa, chill out!"

    if not str:
        return "Fine. Be that way!"

    if str.endswith("?"):
        return "Sure."

    return "Whatever."

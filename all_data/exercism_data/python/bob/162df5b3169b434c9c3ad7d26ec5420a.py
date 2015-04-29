def hey(t):
    if t.strip() == "":
        return  "Fine. Be that way!"

    if t.isupper():
        return "Whoa, chill out!"

    if t.endswith('?'):
        return "Sure."

    return "Whatever."

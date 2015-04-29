def hey(t):
    if t.strip() == "":
        return  "Fine. Be that way!"

    if t.isupper():
        return "Whoa, chill out!"

    if t[-1]=='?':
        return "Sure."

    return "Whatever."

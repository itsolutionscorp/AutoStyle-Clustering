def hey(text):
    text = text.strip()

    if not text:
        return "Fine. Be that way!"
    elif text.isupper():
        return "Whoa, chill out!"
    elif text[-1] == '?':
        return "Sure."
    else:
        return "Whatever."

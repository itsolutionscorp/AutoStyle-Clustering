def hey(text):
    if not text.strip():
        return "Fine. Be that way!"
    elif text==text.upper() and len([c for c in text if c.isalpha()]):
        return "Woah, chill out!"
    elif text[-1]=="?":
        return "Sure."
    else:
        return "Whatever."

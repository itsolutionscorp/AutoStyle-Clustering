def hey(msg):
    if msg.isupper() == True:
        return "Whoa, chill out!"
    elif msg.endswith("?"):
        return "Sure."
    elif msg.isspace():
        return "Fine. Be that way!"
    elif len(msg) < 1:
        return "Fine. Be that way!"
    else:
        return "Whatever."

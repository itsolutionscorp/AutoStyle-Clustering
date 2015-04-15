def hey(msg):
    if "".join(msg.split()) == "":
        return "Fine. Be that way!"
    elif any(c.isalpha() for c in msg) and msg.upper() == msg:
        return "Woah, chill out!"
    elif msg[-1] == "?":
        return "Sure."
    else:
        return "Whatever."

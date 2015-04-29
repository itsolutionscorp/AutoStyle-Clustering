def hey(msg):
    msg = msg.strip()
    if (msg == ""):
        return "Fine. Be that way!"
    elif msg.isupper():
        return "Whoa, chill out!"
    elif msg.endswith("?"):
        return "Sure."
    else:
        return "Whatever."

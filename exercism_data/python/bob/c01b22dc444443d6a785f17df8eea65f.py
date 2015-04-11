def hey(msg):
    if msg.isupper():
        return "Whoa, chill out!"
    elif msg.endswith("?"):
        return "Sure."
    elif not msg.isprintable() or len(msg) == 0:
        return "Fine. Be that way!"
    else:
        return "Whatever."

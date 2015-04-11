def hey(msg):
    if msg.isupper():
        return "Whoa, chill out!"
    elif msg.endswith("?"):
        return "Sure."
    elif msg.strip() == "":
        return "Fine. Be that way!"
    else:
        return "Whatever."

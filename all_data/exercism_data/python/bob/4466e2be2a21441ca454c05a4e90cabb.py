CHILL = "Woah, chill out!"
SURE = "Sure."
FINE = "Fine. Be that way!"
WHATEVER = "Whatever."

def hey(msg):
    if not msg.strip():
        return FINE
    elif msg.isupper():
        return CHILL
    elif msg.endswith('?'):
        return SURE
    else:
        return WHATEVER

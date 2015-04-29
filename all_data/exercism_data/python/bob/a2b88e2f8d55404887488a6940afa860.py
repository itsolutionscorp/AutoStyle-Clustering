"""Bob, the lackadaisical teenager."""

def hey(msg):
    if not msg.strip():
        return "Fine. Be that way!"
    if msg.isupper():
        return "Woah, chill out!"
    if msg.endswith("?"):
        return "Sure."
    return "Whatever."

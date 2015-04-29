class Bob:
    def __init__(self):
        pass
    def hey(self, msg):
        if msg.isupper():
            return "Woah, chill out!"
        elif msg.endswith("?"):
            return "Sure."
        elif len(msg.strip()) == 0:
            return "Fine. Be that way!"
        else:
            return "Whatever."

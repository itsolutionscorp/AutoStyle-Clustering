class Bob:

    def hey(self, msg):
        if not msg or not msg.strip():
            return "Fine. Be that way!"
        if all(map(lambda c: c.isupper() or not c.isalpha(), msg)):
            return "Woah, chill out!"
        if msg.endswith('?'):
            return "Sure."
        return "Whatever."

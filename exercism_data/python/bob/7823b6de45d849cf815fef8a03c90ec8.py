class Bob:

    def hey(self, message):
        message = (message or "").strip()
        if not message:
            return "Fine. Be that way!"
        if message.isupper():
            return "Woah, chill out!"
        if message.endswith('?'):
            return "Sure."
        return "Whatever."
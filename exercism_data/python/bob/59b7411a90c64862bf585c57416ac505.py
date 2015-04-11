class Bob():

    def hey(self, message):
        if not message or not message.strip():
            return "Fine. Be that way!"
        if message.upper() == message:
            return "Woah, chill out!"
        if message.endswith("?"):
            return "Sure."
        return "Whatever."

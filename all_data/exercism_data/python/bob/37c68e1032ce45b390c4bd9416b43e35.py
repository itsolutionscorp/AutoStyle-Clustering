class Bob:
    def hey(self, message):
        if message == None or len(message.strip()) == 0:
            return "Fine. Be that way!"
        if message == message.upper():
            return "Woah, chill out!"
        if message[-1] == '?':
            return "Sure."
        return "Whatever."

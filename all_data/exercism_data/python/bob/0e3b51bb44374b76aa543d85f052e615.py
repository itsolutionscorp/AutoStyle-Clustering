class Bob:
    def hey(self, message):
        if (message == None or message == '' or message.isspace()):
            return "Fine. Be that way."
        if (message == message.upper()): # Message is shouting.
            return "Woah, chill out!"
        if (message[-1] == "?"):
            return "Sure."
        return "Whatever."

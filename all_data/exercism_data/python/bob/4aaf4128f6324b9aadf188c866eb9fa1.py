class Bob:
    def hey(self, message):
        if self.isQuiet(message):
            return "Fine. Be that way."
        if self.isShouting(message):
            return "Woah, chill out!"
        if self.isAsking(message):
            return "Sure."
        return "Whatever."
    def isQuiet(self, message):
        return not message or message.isspace()
    def isShouting(self, message):
        return message.isupper()
    def isAsking(self, message):
        return message.endswith("?")

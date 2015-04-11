class Bob:
    def hey(self, message):
        if not message:
            return self.hey_message("")
        else:
            return self.hey_message(message.strip())

    def hey_message(self, message):
        if not message:
            return "Fine. Be that way!"
        elif message.isupper():
            return "Woah, chill out!"
        elif message[-1] == "?":
            return "Sure."
        return "Whatever."

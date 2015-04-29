class Bob:
    def hey(self, message):
        self.message = message
        if self.message_is_silence():
            return "Fine. Be that way!"
        elif self.message_is_shouting():
            return "Woah, chill out!"
        elif self.message_is_question():
            return "Sure."
        else:
            return "Whatever."

    def message_is_silence(self):
        return not self.message.strip()

    def message_is_shouting(self):
        return self.message.isupper()

    def message_is_question(self):
        return self.message.endswith('?')

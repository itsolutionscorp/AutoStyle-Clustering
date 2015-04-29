import string


class Bob:
    ONLY_NUMBERS = string.punctuation + string.digits + ' '

    def hey(self, message):
        self.message = message
        if self.message_is_silence():
            return "Fine. Be that way!"
        elif self.message_is_shouting():
            return "Woah, chill out!"
        else:
            return self.respond_calmly()

    def respond_calmly(self):
        if self.message.endswith('?'):
            return "Sure."
        else:
            return "Whatever."

    def message_is_silence(self):
        return not self.message.strip()

    def message_is_shouting(self):
        return self.message.upper() == self.message \
               and not self.message_is_only_numbers()

    def message_is_only_numbers(self):
        return all((c in self.ONLY_NUMBERS for c in self.message))

# encoding: utf-8

class Bob:
    def hey(self, text):
        if text:
            text = text.strip()

        if self.is_silence(text):
            return "Fine, be that way."
        elif self.is_shouting(text):
            return "Woah, chill out!"
        elif self.is_question(text):
            return "Sure."
        else:
            return "Whatever."

    def is_silence(self, text):
        return not bool(text)

    def is_shouting(self, text):
        return text.isupper()

    def is_question(self, text):
        return text.endswith('?')
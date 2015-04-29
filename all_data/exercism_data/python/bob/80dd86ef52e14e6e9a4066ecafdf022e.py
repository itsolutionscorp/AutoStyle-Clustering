import re

class Bob:
    def hey(self, message):
        if self.is_silence(message):
            return "Fine. Be that way!"
        elif self.is_yelling(message):
            return 'Woah, chill out!'
        elif self.is_question(message):
            return "Sure."
        else:
            return "Whatever."

    def is_silence(self, message):
        return message.strip() == ""

    def is_question(self, message):
        return message[-1] == "?"

    def is_yelling(self, message):
        """Ensure that we have letters, and that the message
        is in all caps (locale independent"""
        if re.findall('[a-zA-Z]', message):
            return message.upper() == message
        return False

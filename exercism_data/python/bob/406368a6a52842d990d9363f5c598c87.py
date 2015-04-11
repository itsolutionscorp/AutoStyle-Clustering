class Bob(object):
    def hey(self, message):
        if self._is_quiet(message):
            return "Fine. Be that way."
        if self._is_shouting(message):
            return "Woah, chill out!"
        if self._is_asking(message):
            return "Sure."
        return "Whatever."

    def _is_quiet(self, message):
        return not message or message.isspace()

    def _is_shouting(self, message):
        return message.isupper()

    def _is_asking(self, message):
        return message.endswith("?")

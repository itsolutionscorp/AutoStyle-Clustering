class Bob:

    QUESTION_RESPONSE = 'Sure.'
    YELL_RESPONSE = 'Woah, chill out!'
    SILENCE_RESPONSE = 'Fine. Be that way.'
    FALLBACK_RESPONSE = 'Whatever.'

    def hey(self, text):
        if self.is_silence(text):
            return Bob.SILENCE_RESPONSE

        elif self.is_yell(text):
            return Bob.YELL_RESPONSE

        elif self.is_question(text):
            return Bob.QUESTION_RESPONSE

        else:
            return Bob.FALLBACK_RESPONSE

    def is_question(self, text):
        return text.endswith('?')

    def is_yell(self, text):
        return text.isupper()

    def is_silence(self, text):
        return text is None or text == '' or text.isspace()

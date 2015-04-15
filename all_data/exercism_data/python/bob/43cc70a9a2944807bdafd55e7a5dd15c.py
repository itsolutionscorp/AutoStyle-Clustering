class Bob:

    QUESTION_RESPONSE = 'Sure.'
    YELL_RESPONSE = 'Woah, chill out!'
    SILENCE_RESPONSE = 'Fine. Be that way.'
    FALLBACK_RESPONSE = 'Whatever.'

    def hey(self, text):
        if self.isSilence(text):
            return Bob.SILENCE_RESPONSE

        elif self.isYell(text):
            return Bob.YELL_RESPONSE

        elif self.isQuestion(text):
            return Bob.QUESTION_RESPONSE

        else:
            return Bob.FALLBACK_RESPONSE

    def isQuestion(self, text):
        return text[-1] == '?'

    def isYell(self, text):
        return text.isupper()

    def isSilence(self, text):
        return text is None or text == '' or text.isspace()

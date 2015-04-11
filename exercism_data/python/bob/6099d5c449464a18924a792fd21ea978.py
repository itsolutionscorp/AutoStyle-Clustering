# bob
import string

class Bob(object):

    def hey(self, spoken):
        words = spoken.strip()
        # Default response
        response = "Whatever."
        if self.isBlank(words):
            # Response to blank
            response = 'Fine. Be that way!'
        else:
            if self.isQuestion(words):
                # Response to question
                response = 'Sure.'
            if self.isYelling(words):
                # Response to yelling, question or not
                response = 'Woah, chill out!'
        return response

    def isQuestion(self, words):
        return words[-1] == '?'

    def isBlank(self, words):
        return words == ""

    def isYelling(self, words):
        letters = ""
        letters_upper = ""
        for c in words:
            if c in string.ascii_lowercase:
                return False
            # ignore characters that are the same upper or lower
            if c not in string.digits + string.punctuation + string.whitespace:
                letters += c
                letters_upper += c.upper()

        return letters != "" and letters == letters_upper

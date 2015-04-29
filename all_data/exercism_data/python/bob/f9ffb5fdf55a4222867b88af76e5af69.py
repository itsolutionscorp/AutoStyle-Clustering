# bob
import string

class Bob(object):

    RESPONSES = {'blank': 'Fine. Be that way!',
                 'default': 'Whatever.',
                 'question': 'Sure.',
                 'yelling': 'Woah, chill out!'}

    def __init__(self):
        self.filters = [('blank', self.isBlank),
                        ('question', self.isQuestion),
                        ('yelling', self.isYelling)]

    def hey(self, spoken):
        words = spoken.strip()
        # Default response
        response = "default"
        for key, filter_method in self.filters:
            if filter_method(words):
                response = key

        return self.RESPONSES[response]

    def isQuestion(self, words):
        return words and words[-1] == '?'

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

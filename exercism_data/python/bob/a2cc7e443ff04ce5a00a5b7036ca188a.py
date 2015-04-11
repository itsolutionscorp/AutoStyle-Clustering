class Bob(object):
    def hey(self, words):
        phrase = Phrase(words)

        if phrase.is_silent():
            return "Fine, be that way."
        if phrase.is_yelling():
            return "Woah, chill out!"
        if phrase.is_questioning():
            return "Sure."
        return "Whatever."


class Phrase(object):
    def __init__(self, words=''):
        self.words = words.strip()

    def is_questioning(self):
        return self.words.endswith('?')

    def is_yelling(self):
        return self.words.isupper()

    def is_silent(self):
        return not self.words

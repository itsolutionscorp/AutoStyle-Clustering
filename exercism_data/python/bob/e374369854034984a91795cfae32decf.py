class Bob(object):
    def hey(self, words):
        phrase = Phrase(words)

        if phrase.isSilent():
            return "Fine, be that way."
        if phrase.isYelling():
            return "Woah, chill out!"
        if phrase.isQuestioning():
            return "Sure."
        return "Whatever."


class Phrase(object):
    def __init__(self, words=''):
        self.words = words.strip()

    def isQuestioning(self):
        return self.words.endswith('?')

    def isYelling(self):
        return self.words.isupper()

    def isSilent(self):
        return not self.words

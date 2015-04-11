class Phrase:
    def __init__(self, phrase):
        self.phrase = phrase

    def empty(self):
        return not self.phrase

    def yelling(self):
        return self.phrase == self.phrase.upper()

    def question(self):
        return self.phrase.endswith("?")

class Bob:
    def hey(self, words):
        phrase = Phrase(words)
        if phrase.empty():
            return "Fine, be that way."
        elif phrase.yelling():
            return "Woah, chill out!"
        elif phrase.question():
            return "Sure."
        else:
            return "Whatever."

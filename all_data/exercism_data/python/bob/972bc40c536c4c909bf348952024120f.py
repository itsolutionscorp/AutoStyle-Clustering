class Bob:
    def hey(self, phrase):
        phrase = Phrase(phrase)
        if phrase.issilence():
            return "Fine. Be that way."
        elif phrase.isyelling():
            return "Woah, chill out!"
        elif phrase.isquestion():
            return "Sure."
        return "Whatever."

class Phrase:
    def __init__(self, phrase):
        self.phrase = phrase
    def isyelling(self):
        return self.phrase.isupper();
    def isquestion(self):
        return self.phrase.endswith("?")
    def issilence(self):
        return not self.phrase or not self.phrase.strip()

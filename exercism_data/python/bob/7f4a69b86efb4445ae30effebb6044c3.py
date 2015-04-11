class Bob:

    def hey(self, utterance):
        communique = Communique(utterance)
        if communique.is_silent():
            return "Fine. Be that way!"
        if communique.is_yelling():
            return "Woah, chill out!"
        if communique.is_asking():
            return "Sure."
        return "Whatever."

class Communique:

    def __init__(self, communique):
        self.communique = communique or ""

    def is_silent(self):
        return len(self.communique.strip()) == 0

    def is_yelling(self):
        return self.communique.isupper()

    def is_asking(self):
        return self.communique.endswith("?")

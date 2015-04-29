__author__ = 'BinaryFu'

class Bob(object):
    def hey(self, sentence):
        if not sentence or sentence.isspace():
            return "Fine. Be that way!"
        elif sentence.isupper():
            return "Woah, chill out!"
        elif sentence.endswith("?"):
            return "Sure."
        else:
            return "Whatever."

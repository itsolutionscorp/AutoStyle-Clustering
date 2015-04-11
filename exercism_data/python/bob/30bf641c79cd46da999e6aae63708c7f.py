import re


class Interpretation(object):
    def __init__(self, sentence):
        self.sentence = sentence

    def is_shout(self):
        regex = re.compile(u'[\W\d]+', re.UNICODE)
        letters = re.sub(regex, '', self.sentence)
        return letters and letters.upper() == letters

    def is_question(self):
        return self.sentence and self.sentence[-1] == '?'

    def is_silence(self):
        regex = re.compile(u'\s', re.UNICODE)
        return not re.sub(regex, '', self.sentence)


class Bob(object):
    def hey(self, sentence):
        interpretation = Interpretation(sentence)
        if interpretation.is_shout():
            return "Woah, chill out!"
        elif interpretation.is_question():
            return "Sure."
        elif interpretation.is_silence():
            return "Fine. Be that way!"
        else:
            return "Whatever."

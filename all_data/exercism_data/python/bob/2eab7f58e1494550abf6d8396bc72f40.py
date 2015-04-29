import re


class Bob:
    @staticmethod
    def is_shout(sentence):
        regex = re.compile(u'[\W\d]+', re.UNICODE)
        letters = re.sub(regex, '', sentence)
        return letters and letters.upper() == letters

    @staticmethod
    def is_question(sentence):
        return sentence and sentence[-1] == '?'

    @staticmethod
    def is_silence(sentence):
        regex = re.compile(u'\s', re.UNICODE)
        return not re.sub(regex, '', sentence)

    def hey(self, sentence):
        if Bob.is_shout(sentence):
            return "Woah, chill out!"
        elif Bob.is_question(sentence):
            return "Sure."
        elif Bob.is_silence(sentence):
            return "Fine. Be that way!"
        else:
            return "Whatever."

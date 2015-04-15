import re


class Phrase(object):
    def __init__(self, phrase):
        self.phrase = phrase

    def word_count(self):
        result = {}

        for word in re.sub(r'[^a-zA-Z0-9 ]', '', self.phrase).split():
            word = word.lower()
            result[word] = result.get(word, 0) + 1

        return result

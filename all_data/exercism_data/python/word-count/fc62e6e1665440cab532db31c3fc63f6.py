import re

class Phrase:

    def __init__(self, phrase):
        self.phrase = phrase.lower().translate(None, ',:!@$%^&')

    def word_count(self):
        words = self.phrase.split(' ')
        result = {}
        for word in words:
            if word != '':
                if word in result.keys():
                    result[word] += 1
                else:
                    result[word] = 1
        return result

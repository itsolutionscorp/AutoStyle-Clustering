import re

class Phrase:
    def __init__(self, phrase):
        self.phrase = phrase

    def word_count(self):
        count = {}
        words = re.findall('[a-zA-Z0-9]+', self.phrase)

        for word in words:
            word = word.lower()

            if word not in count:
                count[word] = 0

            count[word] += 1

        return count

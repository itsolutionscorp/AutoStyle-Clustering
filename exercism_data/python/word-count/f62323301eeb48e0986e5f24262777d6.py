import re
class Phrase:
    def __init__(self, phrase):
        self.phrase = phrase
    def word_count(self):
        phrase = self.phrase
        phrase = re.sub(r'\W+', ' ', phrase)
        array = phrase.lower().split()
        word_set = set(array)
        counts = {}
        for word in word_set:
            counts[word] = array.count(word)
        return counts

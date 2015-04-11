import re
class Phrase:
    def __init__(self, phrase):
        self.phrase = phrase

    def word_count(self):
        array = self.clean_split(self.phrase)
        word_set = set(array)
        counts = {}
        for word in word_set:
            counts[word] = array.count(word)
        return counts

    @staticmethod
    def clean_split(phrase):
        return re.sub(r'\W+', ' ', phrase).lower().split()

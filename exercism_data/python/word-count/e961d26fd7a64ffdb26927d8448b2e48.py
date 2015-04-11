import re

class Phrase():

    phrase = ""

    def __init__(self, phrase):
        self.phrase = phrase

    def word_count(self):
        # do not change self.phrase
        count_phrase = self.phrase.lower()
        #remove punctuation
        count_phrase = re.sub(r"[.,:!&@$%^]", "", count_phrase)
        count = dict()
        for i in count_phrase.split():
            if i in count:
                count[i] += 1
            else:
                count[i] = 1
        return count

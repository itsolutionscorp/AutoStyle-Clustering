import re


class Phrase:

    def __init__(self, phrase):
        self.phrase = phrase

    def word_count(self):
        #chop the phrase into words, minus punctuation
        alpha_only = re.compile('\w+')
        words = alpha_only.findall(self.phrase.lower())

        #count the occurrences of each word
        tally = {}
        for word in words:
            tally[word] = tally.get(word, 0) + 1

        return tally

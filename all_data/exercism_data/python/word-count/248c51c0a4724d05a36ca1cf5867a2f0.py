from collections import Counter
import re

class Phrase(object):
    def __init__(self, phrase):
        self.phrase = phrase

    def word_count(self):
        """
        Break the phrase into words and return the count of unique words in a dict
        """

        word_pattern = re.compile(r'\b\w+\b')
        words = re.findall(word_pattern, self.phrase)

        for i in range(0, len(words)):
            words[i] = words[i].lower()

        result = Counter(words)

        return dict(result.most_common())

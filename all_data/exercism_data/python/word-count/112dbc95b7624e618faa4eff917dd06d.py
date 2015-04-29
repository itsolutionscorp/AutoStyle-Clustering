import re


class Phrase(object):
    def __init__(self, phrase):
        self.phrase = phrase

    def word_count(self):
        # Split phrase to words
        words = re.split('[^a-z0-9]+', self.phrase.lower())

        # Remove empty strings from words -- they might be there after split if
        # phrase ends with non-word sequence.
        words = [p for p in words if p]

        # Count individual words
        counts = {}
        for word in words:
            counts[word] = counts.setdefault(word, 0) + 1

        return counts

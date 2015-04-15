import re

class Phrase:

    def __init__(self, phrase):
        self.phrase = phrase.split(' ')

    def scrub_words(self):
        words = []

        for p in self.phrase:
            if re.search(r'[a-zA-Z]', p):
                words.append(re.sub(r'[0-9!@#$%^&*(),]','',p).lower())
            elif re.search(r'[0-9]+', p):
                words.append(re.search(r'[0-9]+',p).group())

        return words

    def word_count(self):
        freq = {}
        words = self.scrub_words()

        for word in words:
            if word in freq:
                freq[word] += 1
            else:
                freq[word] = 1
        return freq

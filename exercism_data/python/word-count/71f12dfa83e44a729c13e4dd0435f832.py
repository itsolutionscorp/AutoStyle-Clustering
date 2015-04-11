class Phrase:
    def __init__(self, phrase):
        wordlist = [Phrase._sanitze(word) for word in phrase.split()]
        self._words = {word: wordlist.count(word) for word in wordlist if word}

    def _sanitze(word):
        return ''.join([c.lower() for c in word if c.isalnum()])


    def word_count(self):
        return self._words

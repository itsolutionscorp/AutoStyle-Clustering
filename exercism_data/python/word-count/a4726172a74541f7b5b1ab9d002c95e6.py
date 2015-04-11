from collections import Counter

class Phrase:
    def __init__(self, phrase):
        self.phrase = phrase

    def word_count(self):
        norm = ''.join(ch.lower() for ch in self.phrase if ch.isalnum() or ch.isspace())
        return Counter(norm.split())

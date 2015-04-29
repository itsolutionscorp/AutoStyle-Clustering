from collections import Counter


class Phrase(Counter):
    def __init__(self, s):
        return super().__init__(
            "".join(c.lower() for c in s if c.isalnum() or c == " ").split())

    def word_count(self):
        return self

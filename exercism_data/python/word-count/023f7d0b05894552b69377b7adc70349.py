class Phrase:

    def __init__(self, phrase):
        self.phrase = phrase

    def word_count(self):
        import re
        from collections import defaultdict
        words = re.findall('\w+', self.phrase)
        count = defaultdict(int)
        for word in words:
          count[word.lower()] += 1
        return count

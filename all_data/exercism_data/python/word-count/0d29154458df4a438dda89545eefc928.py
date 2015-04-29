from collections import defaultdict

def _simplify_word(word):
    return ''.join([c.lower() for c in word if c.isalnum()])

class Phrase:
    def __init__(self, phrase):
        self.phrase = phrase

    def word_count(self):
        words = self.phrase.split()

        result = defaultdict(int)
        for word in words:
            result[_simplify_word(word)] += 1

        if '' in result:
            del(result[''])

        return dict(result)
            

        

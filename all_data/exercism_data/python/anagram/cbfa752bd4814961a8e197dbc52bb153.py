class Anagram:
    def __init__(self, word):
        self.word = word
        self.canonical = Anagram.canonicalize(word)
        return

    def match(self, words):
        return [ w for w in words
                 if self.is_true_anagram(w) ]

    def is_true_anagram(self, word):
        '''
        A true anagram is a different word with the same
        canonical representation.
        '''
        return ( word != self.word
                 and Anagram.canonicalize(word) == self.canonical )

    @staticmethod
    def canonicalize(word):
        '''
        The canonical representation is all the letters in
        alphabetical order, lower-cased.
        '''
        letters = list(word.lower())
        letters.sort()
        return letters

    pass

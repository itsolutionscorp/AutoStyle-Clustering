class Anagram:

    def __init__(self, word):
        # store the word for direct comparison
        self.source_word = word
        # get letter frequency once for the word
        self.source_letters = self._count_letters(word)
        # store the dict length to avoid multiple calculations
        self.source_letters_len = len(self.source_letters)

    def _count_letters(self, word):
        '''
        builds a map {letter: frequency in word}
        '''
        m = {}
        for c in word.lower():
            m[c] = m[c] + 1 if c in m else 1
        return m

    def match(self, words):
        res = []
        for w in words:
            # skip further checking if it's the same word
            if w is self.source_word:
                continue
            # get letter frequency O(n)
            letters = self._count_letters(w)
            # skip word if number of different letters differs
            if len(letters) != self.source_letters_len:
                continue
            # compare the letters frequencies
            is_anagram = True
            for k in self.source_letters:
                if k not in letters or letters[k] != self.source_letters[k]:
                    is_anagram = False
            if is_anagram:
                res.append(w)
        return res

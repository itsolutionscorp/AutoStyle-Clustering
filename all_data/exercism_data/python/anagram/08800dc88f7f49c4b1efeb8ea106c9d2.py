class Anagram:
    def __init__(self, word):
        self.word = word.lower()
        self.word_as_sorted_list = sorted([i for i in self.word])

    def match(self, words):
        ok = []
        for candidate in words:
            test_candidate = candidate.lower()
            if test_candidate != self.word and \
               sorted([i for i in test_candidate]) == self.word_as_sorted_list:
                ok.append(candidate)
        return ok

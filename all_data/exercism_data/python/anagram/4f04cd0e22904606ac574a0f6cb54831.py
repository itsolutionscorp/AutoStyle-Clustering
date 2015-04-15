class Anagram():
    def __init__(self, word):
        self._word = word

    def match(self, candidates):
        anagram_list = []
        word = self._word.lower()
        for candidate in candidates:
            if candidate == word:
                continue
            candidate_chars = list(candidate.lower())
            equal_chars = True
            for char in candidate_chars:
                if char not in word:
                    equal_chars = False
                elif word.count(char) != candidate.lower().count(char):
                    equal_chars = False

            if equal_chars and len(candidate) == len(word):
                anagram_list.append(candidate)

        return anagram_list

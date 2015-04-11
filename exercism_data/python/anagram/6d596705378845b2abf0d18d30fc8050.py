class Anagram(object):
    """ Anagram class """
    def __init__(self, base_word):
        self.base_word = base_word.lower()

    def match(self, candidates):
        """ Get matching anagrams

        @param candidates: a list of strings, which are potential anagrams
        @return: a list of matched anagrams
        """
        anagrams = []
        for candidate in candidates:
            if self.is_anagram(candidate.lower()):
                anagrams.append(candidate)
        return anagrams

    def is_anagram(self, candidate):
        """ Check if the 2 words are anagrams

        @param candidate: potential anagrams
        @return: if the words are anagrams return True, otherwise False
        """
        if self.base_word == candidate:
            return False
        base_chars = [char for char in self.base_word]
        candidate_chars = [char for char in candidate]
        base_chars.sort()
        candidate_chars.sort()

        return base_chars == candidate_chars

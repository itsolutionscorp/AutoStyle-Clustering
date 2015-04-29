class Anagram:
    """ For finding anagrams for a given input word. """
    def __init__(self, input_word):
        # Store input word for comparison against candidate anagrams
        self.target = input_word
        # Want to avoid repeatedly sorting the letters of the target word
        # Sorting repeated candidates should be a harmless inefficiency for now
        self.target_letter_order = self.letter_order(self.target)

    def is_anagram(self, candidate):
        """ Anagram check based on sorted letter counts. """
        if self.target_letter_order == self.letter_order(candidate):
            # Same number of letters is possibly anagram
            if self.target != candidate:
                # Just remember that a word is not its own anagram
                return True
        # Candidate is not an anagram
        return False

    def letter_order(self, word):
        """ Returns a list of the letters in sorted order, ignoring case. """
        return sorted(word.lower())

    def match(self, anagram_candidates):
        """ Look for candidate anagrams for a given list. """
        hit_list = []
        for candidate in anagram_candidates:
            if self.is_anagram(candidate):
                # found a an anagram to add to the output list
                hit_list.append(candidate)
        return hit_list

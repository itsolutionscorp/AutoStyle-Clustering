class Anagram(object):

    """An anagram"""

    def __init__(self, anagram):
        """Create a new anagram from given string."""
        self.anagram = anagram.lower()

    def match_word(self, word):
        """Return True if anagram matches the given word."""
        return (sorted(word.lower()) == sorted(self.anagram) and
                not word.lower() == self.anagram)

    def match(self, word_list):
        """Match anagram to given word_list and return list of matches."""
        matches = []
        for word in word_list:
            if self.match_word(word):
                matches.append(word)
        return matches

def _normalize_word(word):
    """Return a word with its letters sorted and lowercased"""
    return ''.join(sorted(word.lower()))


class Anagram(object):
    """Recognize anagrams of a specific word"""

    def __init__(self, word):
        """While saving the word, also save a normalized version"""
        self.word = word
        self._normalized_word = _normalize_word(word)

    def match(self, choices):
        """Return any anagrams of the initial word"""
        matches = []
        for choice in choices:
            if choice == self.word:
                continue  # Words aren't anagrams of themselves.
            if _normalize_word(choice) == self._normalized_word:
                matches.append(choice)
        return matches

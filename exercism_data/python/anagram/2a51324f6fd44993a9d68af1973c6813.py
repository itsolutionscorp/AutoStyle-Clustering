def convert_word_into_ordered_array_of_letters(word):
    """convert word into an sorted array of letters.

    :word: word
    :returns: ordered list of letters list(str)

    """
    letter_list = list(word.lower())
    letter_list.sort()
    return letter_list


class Anagram(object):

    """Class that find anagrams from given string."""

    def __init__(self, word):
        self._word = word
        self._letter_list = convert_word_into_ordered_array_of_letters(word)

    def match(self, word_list):
        result_anagrams = []
        for word in word_list:
            if self.is_an_anagram(word):
                result_anagrams.append(word)
        return result_anagrams

    def is_an_anagram(self, word):
        """Return if word is an anagram of instance word."""
        return word != self._word and self._letter_list == convert_word_into_ordered_array_of_letters(word)

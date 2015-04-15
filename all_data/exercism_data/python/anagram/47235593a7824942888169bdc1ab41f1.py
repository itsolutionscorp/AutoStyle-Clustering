def detect_anagrams(word, possible_anagrams):

    def compare_to_word(possible_anagram):

        if possible_anagram.lower() == word.lower(): # remove exact matches
            return False

        return not cmp(sorted(list(word.lower())), sorted(list(possible_anagram.lower())))

    return filter(compare_to_word, possible_anagrams)

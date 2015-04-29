def detect_anagrams(word, potential_anagrams):
    return [anagram for anagram in potential_anagrams
            if word.lower() != anagram.lower()
            if sorted(word.lower()) == sorted(anagram.lower())]

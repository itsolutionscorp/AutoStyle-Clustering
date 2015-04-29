def detect_anagrams(word, candidates):
    return [anagram for anagram in candidates
            if word.lower() != anagram.lower() and \
                sorted(word.lower()) == sorted(anagram.lower())]

def detect_anagrams(word, potential_anagrams):
    return [anagram for anagram in potential_anagrams
            if word.lower() != anagram.lower()
            if str.join('', sorted(word.lower())) == str.join('', sorted(anagram.lower()))]

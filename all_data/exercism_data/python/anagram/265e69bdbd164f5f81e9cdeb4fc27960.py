def detect_anagrams(word, possibles):
    word_sorted = ''.join(sorted(word.lower()))
    return [anagram for anagram in possibles
            if ''.join(sorted(anagram.lower())) == word_sorted
            and anagram.lower() != word.lower()]

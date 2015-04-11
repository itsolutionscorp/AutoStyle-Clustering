def detect_anagrams(word, possible_anagrams):
    anagrams = []
    
    for possible in possible_anagrams:
        word = word.lower()
        same_words = possible.lower() == word
        is_anagram = sorted(word) == sorted(possible.lower())

        if is_anagram and not same_words:
            anagrams.append(possible)
    return anagrams

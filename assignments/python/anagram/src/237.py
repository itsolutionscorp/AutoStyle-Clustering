def detect_anagrams(word, possible_anagrams):
    anagrams = []
    sorted_word = sorted(word.lower())
    for anagram in possible_anagrams:
        if sorted(anagram.lower()) == sorted_word:
            if anagram.lower() != word.lower():
                anagrams.append(anagram)
    return anagrams

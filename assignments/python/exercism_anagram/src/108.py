def detect_anagrams(word, possible_anagrams):
    anagrams = []
    for anagram in possible_anagrams:
        if sorted(word.lower()) == sorted(anagram.lower()):
            if word.lower() != anagram.lower():
                anagrams.append(anagram)
    return anagrams

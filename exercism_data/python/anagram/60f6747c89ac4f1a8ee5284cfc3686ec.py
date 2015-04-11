def detect_anagrams(word, possible_anagrams):
    result = []
    letters = sorted(word.lower())
    for anagram in possible_anagrams:
        if anagram.lower() == word.lower():
            continue
        if letters == sorted(anagram.lower()):
            result.append(anagram)
    return result

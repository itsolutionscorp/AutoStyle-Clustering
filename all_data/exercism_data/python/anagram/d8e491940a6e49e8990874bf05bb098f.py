from collections import defaultdict

def detect_anagrams(base, candidates):
    base_occurences = count_character_occurences(base.lower())
    anagrams = []
    for candidate in candidates:
        if base_occurences == count_character_occurences(candidate.lower()) and base.lower()!=candidate.lower():
            anagrams.append(candidate)
    return anagrams

def count_character_occurences(word):
    occurences = defaultdict(lambda: 0)
    for character in word:
        occurences[character] += 1
    return occurences

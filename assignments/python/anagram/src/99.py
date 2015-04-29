from itertools import permutations
def detect_anagrams(match_word, words):
    possible_words = list(permutations(match_word.lower()))  # Generates anagrams, doesn't scale!
    match_word_tuple = tuple(match_word.lower())
    results = []
    for word in words:
        word_tuple = tuple(word.lower())
        if word_tuple == match_word_tuple:
            continue
        if word_tuple in possible_words:
            results.append(word)
    return results

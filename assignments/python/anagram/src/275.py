from collections import Counter
def detect_anagrams(word, anagrams):
    # count all characters in word
    word_counter = Counter(word.lower())
    # without collections.Counter:
    # word_counter = {c: word.lower().count(c) for c in word.lower()}
    results = []
    for anagram in anagrams:
        anagram_counter = Counter(anagram.lower())
        if (word_counter == anagram_counter
                and word.lower() != anagram.lower()):
            results.append(anagram)
    return results

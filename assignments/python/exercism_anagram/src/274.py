from collections import Counter
def detect_anagrams(original_word, possible_anagrams):
    original_word_count = Counter(original_word.lower())
    list_of_anagrams = []
    for word in possible_anagrams:
        if original_word_count == Counter(word.lower()) \
                and not word.lower() == original_word.lower():
            list_of_anagrams.append(word)
    return list_of_anagrams

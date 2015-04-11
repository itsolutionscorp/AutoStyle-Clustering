from collections import Counter


def _get_letter_count(the_word):
    letter_counter = Counter()
    for l in the_word.lower():
        letter_counter[l] += 1 
    return letter_counter


def detect_anagrams(the_word, word_list):
    anagrams = []
    main_letter_count = _get_letter_count(the_word)
    for word in word_list:
        if main_letter_count == _get_letter_count(word) and word.lower() != the_word.lower():
            anagrams.append(word)
    return anagrams

from collections import Counter

"""
returns all anagrams of word found in list_of_words
"""
def detect_anagrams(word, list_of_words):
    ret_list = []

    lower_word = word.lower()  # account for case
    word_map = Counter(lower_word)

    for candidate_word in list_of_words:
        lower_candidate_word = candidate_word.lower()  # account for case
        if len(candidate_word) == len(word) and lower_candidate_word != lower_word:  #
            candidate_word_map = Counter(lower_candidate_word)
            if word_map == candidate_word_map:
                ret_list.append(candidate_word)

    return ret_list

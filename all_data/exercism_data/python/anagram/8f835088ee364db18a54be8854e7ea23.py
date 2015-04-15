def sort_word(word):
    return sorted(word.lower())


def detect_anagrams(word, cand_list):
    sorted_word = sort_word(word)
    return [ cand for cand in cand_list if sorted_word == sort_word(cand) and word.lower() != cand.lower() ]

def sort_word(word):
    """Turn word into a sorted, case-insensitive list of letters
    for better sorting.
    """
    sorted_word = list(word.lower())
    sorted_word.sort()
    return sorted_word


def detect_anagrams(word, cand_list):
    sorted_word = sort_word(word)
    anagrams = []
    anagrams = [ cand for cand in cand_list if sorted_word == sort_word(cand) and word.lower() != cand.lower() ]
    return anagrams

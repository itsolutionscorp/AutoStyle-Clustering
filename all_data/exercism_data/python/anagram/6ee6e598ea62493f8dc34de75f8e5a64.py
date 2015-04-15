from itertools import permutations


def detect_anagrams(word, full_list):

    anagram_list = [''.join(p) for p in permutations(word)]
    return_sub_list = []

    for list_word in full_list:
        for anagram in anagram_list:
            if str(anagram).lower() == str(list_word).lower() and str(list_word).lower() != word:
                return_sub_list.append(list_word)
                break

    return return_sub_list

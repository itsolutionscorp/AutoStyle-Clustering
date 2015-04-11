def detect_anagrams(word, candidates):
    '''
    determines which words from candidates are anagrams of word
    '''

    word_list = _str_to_sorted_list(word)

    return [anagram
                for anagram in candidates
                if anagram.lower() != word.lower()
                if _str_to_sorted_list(anagram) == word_list]


def _str_to_sorted_list(word):

    word_list = list(word.lower())
    word_list.sort()
    return word_list

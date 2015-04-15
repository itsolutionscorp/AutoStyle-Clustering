def detect_anagrams(word, candidates):
    '''
    determines which words from candidates are anagrams of word
    '''

    word_list = _sort_str(word)

    return [candidate
                for candidate in candidates
                if candidate.lower() != word.lower()
                if _sort_str(candidate) == word_list]


def _sort_str(word):

    return sorted(word.lower())

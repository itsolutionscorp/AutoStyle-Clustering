def sort_word(w):
    """
    given a string, returns another string with the same letter but sorted alphabetically
    """
    return ''.join(sorted(w))

def detect_anagrams(word, words):
    sorted_word = sort_word(word.lower())
    word_dict = {}
    for w in words:	
        if w.lower() != word.lower():
            word_dict.setdefault(sort_word(w.lower()), []).append(w)
    return word_dict.get(sorted_word, [])

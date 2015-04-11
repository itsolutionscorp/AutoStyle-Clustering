def detect_anagrams(term, search_space):
    word_list = []
    term_dict = make_word_dict(term)

    for word in search_space:
        if len(term) == len(word) and not term.lower() == word.lower():
            word_dict = make_word_dict(word)
            if term_dict == word_dict:
                word_list.append(word)
    return word_list


def make_word_dict(word):
    d = {}
    for char in word.lower():
        if not d.get(char):
            d[char] = 1
        else:
            d[char] = d.get(char) + 1
    return d

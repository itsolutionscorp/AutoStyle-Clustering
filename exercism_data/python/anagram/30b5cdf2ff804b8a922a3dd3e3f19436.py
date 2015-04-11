_normalize = lambda s: ''.join(sorted(s.lower()))
_same_word = lambda x, y: x.lower() == y.lower()


def detect_anagrams(word, word_list):
    word_sorted = _normalize(word)
    list_sorted = (_normalize(w) for w in word_list)
    zipped = zip(word_list, list_sorted)

    return [z[0] for z in zipped
            if z[1] == word_sorted and not _same_word(z[0], word)]

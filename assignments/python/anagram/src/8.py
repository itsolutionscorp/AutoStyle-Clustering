def detect_anagrams(word, candidates):
    result = []
    word_sort = sorted(word.lower())
    for w in candidates:
        if (sorted(w.lower()) == word_sort and w.lower() != word.lower()):
            result.append(w)
    return result

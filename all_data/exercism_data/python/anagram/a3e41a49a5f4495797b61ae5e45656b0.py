def detect_anagrams(word, candidates):
    """Returns list of candidates that are anagrams of word"""
    candidates = (a.lower() for a in candidates)
    word = word.lower()
    results = []

    for e in candidates:
        if e == word:
            break
        copy_list = list(e)
        for letter in word:
            if letter in copy_list:
                copy_list.remove(letter)
            else:
                break
        if not copy_list:
            results.append(e)
    return results

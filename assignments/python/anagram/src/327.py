def detect_anagrams(word, candidates):
    sortedWord = ''.join(sorted(word.lower()))
    result = []
    for w in candidates:
        if sortedWord == ''.join(sorted(w.lower())) and word.lower() != w.lower():
            result.append(w)
    return result

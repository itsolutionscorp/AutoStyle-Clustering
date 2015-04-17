def detect_anagrams(word, candidates):
    sorted_word = sorted(list(word.lower()))
    anagrams = []
    for candidate in candidates:
        if word.lower() == candidate.lower():
            continue
        sorted_candidate = sorted(list(candidate.lower()))
        if sorted_word == sorted_candidate:
            anagrams.append(candidate)
    return anagrams

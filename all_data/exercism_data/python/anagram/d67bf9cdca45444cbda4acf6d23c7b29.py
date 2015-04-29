# !/usr/bin/python


def detect_anagrams(word, candidates):
    word_lower = word.lower()
    sorted_word = sorted(list(word.lower()))
    sorted_candidates = map(lambda s: sorted(list(s.lower())), candidates)

    anagrams = []
    for i in range(len(candidates)):
        if sorted_word == sorted_candidates[i] and \
                not (word_lower == candidates[i].lower()):
            anagrams.append(candidates[i])
    return anagrams

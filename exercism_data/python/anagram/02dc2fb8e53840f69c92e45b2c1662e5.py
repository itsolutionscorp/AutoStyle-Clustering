def detect_anagrams(word, candidates):
    word = sorted(word)
    return filter(lambda candidate_word: sorted(candidate_word) == word,
                  candidates)

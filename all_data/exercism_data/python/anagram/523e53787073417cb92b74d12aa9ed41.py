def detect_anagrams(word, candidates):
    without_equals = filter(lambda candidate: word.lower() != candidate.lower(), candidates)
    return filter(lambda candidate: sorted(word.lower()) == sorted(candidate.lower()), without_equals)

def normalize(word):
    word = word.lower()
    word = ''.join(sorted(word))
    return word

def detect_anagrams(target, candidates):
    anagrams = []
    normalized_target = normalize(target)
    for candidate in candidates:
        normalized_candidate = normalize(candidate)
        if target.lower() != candidate.lower() \
        and normalized_target == normalized_candidate:
            anagrams.append(candidate)

    return anagrams

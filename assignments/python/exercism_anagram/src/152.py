def detect_anagrams(word, candidates):
    correct = []
    for candidate in candidates:
        if ''.join(sorted(word.lower())) == ''.join(sorted(candidate.lower())) and word.lower() != candidate.lower():
            correct.append(candidate)
    return correct

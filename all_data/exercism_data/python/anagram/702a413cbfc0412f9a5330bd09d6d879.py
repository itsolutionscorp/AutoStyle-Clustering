def detect_anagrams(word, candidates):
    return [ 
        candidate for candidate in candidates
        if _is_anagram(word, candidate)
    ]

def _is_anagram(word, candidate):
    word = word.lower()
    candidate = candidate.lower()
    return word != candidate and _sort(word) == _sort(candidate)

def _sort(word):
    return "".join(sorted(word))

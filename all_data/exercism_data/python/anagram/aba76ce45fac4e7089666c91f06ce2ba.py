def squash(word):
    return ''.join(sorted(word.lower()))


def detect_anagrams(word, candidates):
    letters = squash(word)

    return [candidate for candidate in candidates
                if candidate.lower() != word.lower() and squash(candidate) == letters]

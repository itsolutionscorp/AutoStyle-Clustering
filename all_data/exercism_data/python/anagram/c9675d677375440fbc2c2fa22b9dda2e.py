def detect_anagrams(word, candidates):
    return [a for a in candidates if helper(a) == helper(word) and a.lower() != word.lower()]


def helper(word):
    letters = list(word.lower())
    letters.sort()
    return letters

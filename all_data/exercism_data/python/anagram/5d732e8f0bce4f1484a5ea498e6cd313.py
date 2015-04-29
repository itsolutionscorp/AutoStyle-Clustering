import collections


def detect_anagrams(word, candidates):
    word = word.lower()

    target_counts = collections.Counter(word)
    return [c for c in candidates if _is_anagram(word, target_counts, c)]


def _is_anagram(word, target_counts, candidate):
    candidate = candidate.lower()
    return candidate != word and collections.Counter(candidate) == target_counts

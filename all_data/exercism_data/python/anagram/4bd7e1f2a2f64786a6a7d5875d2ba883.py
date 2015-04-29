from collections import Counter

def detect_anagrams(ref, candidates):

    ref = ref.lower()
    refCount = Counter(ref)

    return filter(lambda x: is_anagram(ref, x, refCount), candidates)


def is_anagram(ref, candidate, refCount):

    candidate = candidate.lower()
    return ref != candidate and Counter(candidate) == refCount

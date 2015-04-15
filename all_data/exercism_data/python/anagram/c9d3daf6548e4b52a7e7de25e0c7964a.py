from collections import Counter


def detect_anagrams(source, anagrams):
    # since anagrams in this case are case-insensitive convert all to
    # lowercase
    l_source = source.lower()
    source_count = Counter(l_source)
    res = []

    for pos_anagram in anagrams:
        if Counter(pos_anagram.lower()) == source_count and pos_anagram.lower() != l_source:
            res.append(pos_anagram)

    return res

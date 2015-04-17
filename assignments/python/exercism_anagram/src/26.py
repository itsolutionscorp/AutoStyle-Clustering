from collections import Counter
def detect_anagrams(source, anagrams):
    # since anagrams in this case are case-insensitive convert all to
    # lowercase
    l_source = source.lower()
    source_count = Counter(l_source)
    res = []
    for pos_anagram in anagrams:
        l_pos_anagram = pos_anagram.lower()
        if Counter(l_pos_anagram) == source_count and l_pos_anagram != l_source:
            res.append(pos_anagram)
    return res

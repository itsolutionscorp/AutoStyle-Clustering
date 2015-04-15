from collections import Counter


def detect_anagrams(source, anagrams):
    # since anagrams in this case are case-insensitive convert all to
    # lowercase
    l_source = source.lower()
    source_count = Counter(l_source)
    # list comp instead of loop - less readable but not much .
    return [anagram for anagram in anagrams if Counter(anagram.lower()) == source_count and anagram.lower() != l_source]

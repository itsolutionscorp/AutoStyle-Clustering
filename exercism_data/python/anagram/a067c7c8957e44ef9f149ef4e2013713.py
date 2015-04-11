from collections import Counter


def is_anagram(first, second):
    first = first.lower()
    second = second.lower()

    return first != second and Counter(first) == Counter(second)


def detect_anagrams(original, possible_anagrams):
    return [word for word in possible_anagrams if is_anagram(original, word)]

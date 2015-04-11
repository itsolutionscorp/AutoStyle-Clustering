from collections import Counter

def detect_anagrams(source_word, candidates):
    source_counts = Counter(source_word.lower())

    def is_anagram(candidate):
        if candidate.lower() == source_word.lower(): return False
        return source_counts == Counter(candidate.lower())

    return filter(is_anagram, candidates)

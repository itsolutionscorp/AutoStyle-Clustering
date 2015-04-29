from collections import Counter
def detect_anagrams(needle, haystack):
    needle_counter = Counter(needle.lower())
    return [h for h in haystack if Counter(h.lower()) == needle_counter and h.lower() != needle.lower()]

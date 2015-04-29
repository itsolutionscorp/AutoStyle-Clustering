def detect_anagrams(pin,haystack):
    return [a for a in haystack if sorted(list(a.lower())) == sorted(list(pin.lower())) if a.lower() != pin.lower()]

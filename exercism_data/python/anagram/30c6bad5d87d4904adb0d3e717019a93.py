def detect_anagrams(key, candidates):
    return list(
        filter(lambda x: len(x) == len(key) and
               x.lower() != key.lower() and
               sorted(x.lower()) == sorted(key.lower()),
               candidates))

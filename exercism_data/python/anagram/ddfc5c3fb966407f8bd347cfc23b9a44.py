def detect_anagrams(base, candidates):
    sort_n_lower = lambda w: "".join(sorted(w.lower()))
    test_base = sort_n_lower(base)
    anagrams = [w for w in candidates if sort_n_lower(w) == test_base and w.lower() != base]
    return anagrams

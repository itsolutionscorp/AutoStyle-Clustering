def detect_anagrams(word, wordlist):
    normalize = str.upper
    canonicalize = lambda w: ''.join(sorted(list(w)))
    n = normalize(word)
    c = canonicalize(n)
    def keep(w):
        u = normalize(w)
        return canonicalize(u) == c and u != n

    return filter(keep, wordlist)

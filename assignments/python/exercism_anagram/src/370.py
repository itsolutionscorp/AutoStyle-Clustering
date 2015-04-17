"""anagram function"""
def detect_anagrams(word, slist):
    """detect_anagrams in slist"""
    makelist = lambda s: sorted(s.lower())
    slist = [s for s in slist if len(s) == len(word)]
    wlist = makelist(word)
    return [w for w in slist
            if (makelist(w) == wlist) and (w.lower() != word.lower())]

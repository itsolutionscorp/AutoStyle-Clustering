def detect_anagrams(orig, sent):
    return [ x for x in sent if not orig.lower() == x.lower() and \
        sorted(orig.lower()) == sorted(x.lower())]

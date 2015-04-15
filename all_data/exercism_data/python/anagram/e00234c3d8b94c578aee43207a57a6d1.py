def create_dict(w):
    d = {}
    for letter in w:
        try:
            d[letter] += 1
        except KeyError:
            d[letter] = 1
    return d

def detect_anagrams(word, ls):
    r = []
    list_dicts = [create_dict(w) for w in ls]
    word_dict = create_dict(word)
    for i in xrange(0, len(ls)):
        ld = list_dicts[i]
        if ld == word_dict:
            r.append(ls[i])
    return r

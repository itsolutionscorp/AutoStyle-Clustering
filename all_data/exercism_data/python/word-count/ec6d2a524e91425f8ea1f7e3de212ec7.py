def word_count(phrase):
    p = phrase.split()
    return dict([(w, p.count(w)) for w in set(p)])

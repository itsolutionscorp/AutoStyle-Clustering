def word_count(phrase):
    tmp=phrase.split()
    return {e:tmp.count(e) for e in tmp}

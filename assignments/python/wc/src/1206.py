def word_count(phrase):
    words = phrase.split()
    return {w : words.count(w) for w in words}

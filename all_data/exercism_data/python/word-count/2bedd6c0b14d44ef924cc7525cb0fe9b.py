def word_count(phrase):
    words = phrase.split()
    unique = set(words)
    return {w: words.count(w) for w in unique}

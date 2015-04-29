def word_count(phrase):
    words = phrase.split()
    result = dict(zip(words, [words.count(word) for word in words]))
    return result

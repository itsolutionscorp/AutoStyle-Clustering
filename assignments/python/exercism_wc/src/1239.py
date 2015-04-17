def word_count(s):
    words = s.split()
    return {word:words.count(word) for word in set(words)}

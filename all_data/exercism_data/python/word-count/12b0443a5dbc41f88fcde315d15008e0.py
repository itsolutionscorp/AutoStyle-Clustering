def word_count(words):
    words = words.split()
    word_counts = {word: words.count(word) for word in words}
    return word_counts

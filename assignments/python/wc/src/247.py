def word_count(string):
    words = string.split()
    return {word: words.count(word) for word in words}

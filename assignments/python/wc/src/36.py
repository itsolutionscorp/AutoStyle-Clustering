def word_count(text):
    words = text.split()
    return {word: words.count(word) for word in words}

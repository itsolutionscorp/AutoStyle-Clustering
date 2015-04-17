def word_count(phrase):
    words = [word for word in phrase.split() if word]
    return {word: words.count(word) for word in words}

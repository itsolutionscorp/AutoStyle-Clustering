def word_count(phrase):
    words = {}
    for word in phrase.split():
        words[word] = words.get(word, 0) + 1
    return words

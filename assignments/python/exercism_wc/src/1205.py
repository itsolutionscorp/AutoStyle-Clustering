def word_count(phrase):
    words = {}
    for word in phrase.split():
        if word not in words: words[word] = 0
        words[word] += 1
    return words

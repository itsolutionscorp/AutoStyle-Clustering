def word_count(text):
    words = {}
    for word in text.split():
        count = words.get(word, 0)
        words[word] = count + 1
    return words

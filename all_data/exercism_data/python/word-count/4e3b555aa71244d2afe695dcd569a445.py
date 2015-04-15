def word_count(phrase):
    count = {}
    for word in phrase.split():
        count[word] = count.get(word, 0) + 1
    return count

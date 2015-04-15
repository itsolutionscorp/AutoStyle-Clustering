def word_count(phrase):
    count = dict()
    for word in phrase.split():
        count.setdefault(word, 0)
        count[word] += 1
    return count

def word_count(word):
    counter = {}
    for token in word.split():
        counter[token] = counter.get(token,0) + 1
    return counter

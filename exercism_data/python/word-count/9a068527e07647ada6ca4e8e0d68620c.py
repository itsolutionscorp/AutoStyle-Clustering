def word_count(phrase):
    counter = {}
    word_list = phrase.split()
    for word in word_list:
        counter[word] = counter.get(word,0) + 1
    return counter

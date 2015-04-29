def word_count(words):
    words =  words.split()
    word_count = {}
    for word in (range(len(words))):
        if words[word] not in word_count:
            word_count[words[word]] = 1
        else:
            word_count[words[word]] += 1
    return word_count

def word_count(sentence):
    words = sentence.split()
    count = dict()
    for word in words:
        if word in count:
            count[word] += 1
        else:
            count[word] = 1
    return count

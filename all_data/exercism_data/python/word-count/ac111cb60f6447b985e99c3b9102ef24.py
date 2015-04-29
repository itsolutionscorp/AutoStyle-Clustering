def word_count(sentence):
    #split sentence into words
    words = sentence.split()
    #count words
    count = dict()
    for word in words:
        if word in count:
            count[word] += 1
        else:
            count[word] = 1
    #return dictionary with counts
    return count

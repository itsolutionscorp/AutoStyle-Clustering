def word_count(sentance):
    count = dict()
    words = sentance.split()
    for word in words:
        if word not in count:
            count[word] = 1
        else:
            count[word] += 1
    return count            

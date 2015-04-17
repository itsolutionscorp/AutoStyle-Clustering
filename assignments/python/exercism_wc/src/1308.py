def word_count(words):
    if words is None or not isinstance(words,basestring):
        return None
    word_counts = dict()
    word_list = words.split()
    for word in word_list:
        if word in word_counts:
            word_counts[word] += 1
        else:
            word_counts[word] = 1
    return word_counts

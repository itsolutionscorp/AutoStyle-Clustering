def word_count(s):
    '''
    given a phrase, count the occurrences of each word in that phrase
    '''
    words = s.split();
    word_counts = {}
    for word in words:
        word_counts[word] = word_counts.setdefault(word, 0) + 1
    return word_counts

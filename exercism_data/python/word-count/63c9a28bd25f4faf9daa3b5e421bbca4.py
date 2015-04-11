def word_count(passage):
    '''
    Counts occurrences of each unique word in a phrase.
    1 input (string) --> 1 output (dictionary)
    '''
    words = passage.split()
    result = {}
    for word in set(words):              
        result[word] = words.count(word)
    return result
